package xzy.loshine.nga.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseFragment
import javax.inject.Inject


@ActivityScoped
class LoginFragment @Inject constructor() : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun bindViewModel() {
        addDisposable(viewModel.getMessage()
                .observeOn(schedulerProvider.ui())
                .subscribe { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        web_view.webChromeClient = LoginWebChromeClient()
        web_view.webViewClient = LoginWebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        val callback = JsCallback()
        callback.setOnLoginSuccessListener {
            web_view.post {
                // 操作需要发送回主线程
                val cookieString = CookieManager.getInstance().getCookie(web_view.url)
                if (!TextUtils.isEmpty(cookieString)) {
                    addDisposable(viewModel.saveCookies(cookieString)
                            .observeOn(schedulerProvider.ui())
                            .subscribe({ activity?.finish() }, { it.printStackTrace() }))
                }
            }
        }
        web_view.addJavascriptInterface(callback, "ngaObj")
//        web_view.loadUrl("https://bbs.nga.cn/nuke/p2.htm?login")
        web_view.loadUrl("https://bbs.nga.cn/nuke.php?__lib=login&__act=account&login")
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        web_view.onResume()
    }

    override fun onPause() {
        web_view.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindViewModel()
    }

    override fun onDestroy() {
        web_view?.destroy()
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        return if (web_view.canGoBack()) {
            web_view.goBack()
            true
        } else {
            super.onBackPressed()
        }
    }

    class JsCallback {

        @JavascriptInterface
        fun doAction(action: String, params: Any?) {
            Log.d("doAction", "$action $params")
            if (action == "loginSuccess") {
                onLoginSuccessListener?.invoke()
            }
        }

        private var onLoginSuccessListener: (() -> Unit)? = null

        fun setOnLoginSuccessListener(listener: () -> Unit) {
            this.onLoginSuccessListener = listener
        }
    }

    class LoginWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            // 强制客户端为Android
            view?.loadUrl("javascript:window.parent.__API.get=function(name,params){window.ngaObj.doAction(name,params)};")
        }
    }

    class LoginWebChromeClient : WebChromeClient()
}