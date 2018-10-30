package xzy.loshine.nga.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebViewClient

class ContentWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    constructor(context: Context) : this(context, null)

    fun setLocalMode() {
        webViewClient  = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            }
        }
        val settings = settings
        @SuppressLint("SetJavaScriptEnabled")
        settings.javaScriptEnabled = true

        isFocusableInTouchMode = false
        isFocusable = false
        isLongClickable = false
        isHorizontalScrollBarEnabled = false
        isVerticalScrollBarEnabled = false
        setBackgroundColor(Color.TRANSPARENT)
    }

}