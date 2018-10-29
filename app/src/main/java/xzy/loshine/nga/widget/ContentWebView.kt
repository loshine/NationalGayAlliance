package xzy.loshine.nga.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient


class ContentWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    private var webAppInterface: WebAppInterface? = null

    fun setListener(listener: (Float) -> Unit) {
        webAppInterface?.listener = listener
    }

    fun removeListener() {
        webAppInterface?.listener = null
    }

    constructor(context: Context) : this(context, null)

    init {
        @SuppressLint("SetJavaScriptEnabled")
        settings.javaScriptEnabled = true
        isFocusableInTouchMode = false
        isFocusable = false
        isLongClickable = false
        isHorizontalScrollBarEnabled = false
        isVerticalScrollBarEnabled = false
        setBackgroundColor(Color.TRANSPARENT)

        webAppInterface = WebAppInterface()
        webAppInterface?.let { addJavascriptInterface(it, "AndroidFunction") }
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            }
        }
    }

    private inner class WebAppInterface {

        var listener: ((Float) -> Unit)? = null

        @JavascriptInterface
        fun resize(height: Float) {
            val webViewHeight = height * resources.displayMetrics.density
            //webViewHeight is the actual height of the WebView in pixels as per device screen density
            listener?.invoke(webViewHeight)
        }
    }
}