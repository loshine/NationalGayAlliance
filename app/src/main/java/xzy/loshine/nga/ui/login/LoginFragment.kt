package xzy.loshine.nga.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
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

    private val listener = { cookies: String ->
        addDisposable(viewModel.saveCookies(cookies)
                .observeOn(schedulerProvider.ui())
                .subscribe({ activity?.finish() }, { it.printStackTrace() }))
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // https://bbs.ngacn.cc/nuke.php?__lib=login&__act=account&login
        web_view.webChromeClient = LoginWebChromeClient()
                .also { it.setOnLoginSuccessListener(listener) }
        web_view.webViewClient = LoginWebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
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

    class LoginWebViewClient : WebViewClient()

    class LoginWebChromeClient : WebChromeClient() {
        override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
            if (message?.contains("成功") == true) {
                val cookieString = CookieManager.getInstance().getCookie(view?.url)
                if (!TextUtils.isEmpty(cookieString)) {
                    onLoginSuccessListener?.invoke(cookieString)
                }
            }
            return super.onJsAlert(view, url, message, result)
        }

        private var onLoginSuccessListener: ((String) -> Unit)? = null

        fun setOnLoginSuccessListener(listener: (String) -> Unit) {
            this.onLoginSuccessListener = listener
        }
    }
}