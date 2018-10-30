package xzy.loshine.nga.ui.topic

import android.util.LruCache
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import com.blankj.utilcode.util.SizeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import xzy.loshine.nga.widget.ContentWebView
import javax.inject.Inject


class TopicRowsAdapter
@Inject constructor() : BaseQuickAdapter<TopicRowUiModel, BaseViewHolder>(R.layout.recycler_item_topic_rows) {

    // 缓存 3 页 webView 的高度，解决有图片的时候闪动的问题
    private val heightCache = LruCache<Int, Float>(60)

    override fun convert(helper: BaseViewHolder, item: TopicRowUiModel) {
        helper.setText(R.id.author, item.authorName)
                .setText(R.id.group, item.groupName)
                .setText(R.id.time, item.time)
                .setText(R.id.index, "#${item.index}")
        val contentWebView = helper.getView<ContentWebView>(R.id.content)
        contentWebView.removeListener()
        if (heightCache[helper.adapterPosition] == null) {
            contentWebView.setListener {
                if (helper.adapterPosition > -1 && heightCache[helper.adapterPosition] == null && it != 0f) {
                    heightCache.put(helper.adapterPosition, it)
                }
            }
        }
        val layoutParams = getDefaultConstraintLayoutParams()
        if (heightCache[item.pid] != null) {
            layoutParams.height = heightCache[item.pid]!!.toInt()
        }
        contentWebView.layoutParams = layoutParams

        val html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />${item.content}<script type=\"text/javascript\" src=\"file:///android_asset/collapse_script.js\"></script>"
        contentWebView.loadDataWithBaseURL("file:///android_asset/", html,
                "text/html", "utf-8", "")
        helper.getView<ImageView>(R.id.avatar).let {
            GlideApp.with(it)
                    .load(item.avatar)
                    .placeholder(R.drawable.default_board_icon)
                    .circleCrop()
                    .into(it)
        }
    }

    private fun getDefaultConstraintLayoutParams(): ConstraintLayout.LayoutParams {
        return ConstraintLayout.LayoutParams(0, WRAP_CONTENT).apply {
            endToEnd = PARENT_ID
            startToStart = PARENT_ID
            topToBottom = R.id.group
            marginStart = SizeUtils.dp2px(16f)
            marginEnd = SizeUtils.dp2px(16f)
            topMargin = SizeUtils.dp2px(16f)
        }
    }
}