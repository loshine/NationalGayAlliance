package xzy.loshine.nga.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        web_view.webChromeClient = LoginWebChromeClient()
        web_view.webViewClient = LoginWebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true

        web_view.loadUrl("https://bbs.nga.cn/nuke.php?__lib=login&__act=account&login")
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        web_view.onResume()
        Toast.makeText(context, "登陆成功后需要点击右上角按钮保存 cookies", Toast.LENGTH_LONG).show()
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.toolbar_login, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val cookieString = CookieManager.getInstance().getCookie(web_view.url)
        if (!TextUtils.isEmpty(cookieString)) {
            addDisposable(viewModel.saveCookies(cookieString)
                    .observeOn(schedulerProvider.ui())
                    .subscribe({ activity?.finish() }, { it.printStackTrace() }))
        } else {

        }
        return true
    }

    class LoginWebViewClient : WebViewClient()

    class LoginWebChromeClient : WebChromeClient()
}