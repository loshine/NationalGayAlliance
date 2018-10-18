package xzy.loshine.nga.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebView

class ContentWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    constructor(context: Context) : this(context, null)

    init {
        @SuppressLint("SetJavaScriptEnabled")
        settings.javaScriptEnabled = true
        isFocusableInTouchMode = false
        isFocusable = false
        isLongClickable = false
        setBackgroundColor(Color.TRANSPARENT)
    }
}