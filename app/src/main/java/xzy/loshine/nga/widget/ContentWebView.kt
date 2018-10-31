package xzy.loshine.nga.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import xzy.loshine.nga.ui.image.ImagePreviewActivity

class ContentWebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    constructor(context: Context) : this(context, null)

    private val imageList = ArrayList<String>()

    init {
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val url = request.url.path ?: ""
                if (url.endsWith(".gif") || url.endsWith(".jpg")
                        || url.endsWith(".png") || url.endsWith(".jpeg")
                        || url.endsWith(".bmp")) {
                    val intent = Intent(context, ImagePreviewActivity::class.java)
                    intent.putExtra("image_list", imageList)
                    intent.putExtra("index", imageList.indexOfFirst { it.contains(url) })
                    context.startActivity(intent)
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
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

    fun setImageList(list: Collection<String>) {
        imageList.clear()
        imageList.addAll(list)
    }
}