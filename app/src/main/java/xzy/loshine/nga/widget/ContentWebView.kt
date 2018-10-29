package xzy.loshine.nga.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

class ContentWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    private var listener: ((Int) -> Unit)? = null

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
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

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                post {
                    measure(0, 0)
                    Log.i("zzz", "measuredHeight=$measuredHeight")
                    listener?.invoke(measuredHeight)
                }
            }
        }
    }
}