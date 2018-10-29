package xzy.loshine.nga.ui.topic

import android.util.LruCache
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import xzy.loshine.nga.widget.ContentWebView
import javax.inject.Inject


class TopicRowsAdapter
@Inject constructor() : BaseQuickAdapter<TopicRowUiModel, BaseViewHolder>(R.layout.recycler_item_topic_rows) {

    // 缓存 3 页 webView 的高度，解决有图片的时候闪动的问题
    private val heightCache = LruCache<Int, Int>(60)

    override fun convert(helper: BaseViewHolder, item: TopicRowUiModel) {
        helper.setGone(R.id.subject, item.index == 0)
                .setText(R.id.subject, item.subject)
                .setText(R.id.author, item.authorName)
                .setText(R.id.group, item.groupName)
                .setText(R.id.time, item.time)
                .setText(R.id.index, "#${item.index}")
        val contentWebView = helper.getView<ContentWebView>(R.id.content)
        contentWebView.removeListener()
        contentWebView.setListener {
            if (heightCache[item.pid] == null && it != 0) {
                heightCache.put(item.pid, it)
            }
        }
        if (heightCache[item.pid] != null) {
            contentWebView.layoutParams.height = heightCache[item.pid]!!
        } else {
            contentWebView.layoutParams.height = WRAP_CONTENT
        }
        val html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />${item.content}<script type=\"text/javascript\" src=\"file:///android_asset/collapse_script.js\"></script>"
        contentWebView.loadDataWithBaseURL("file:///android_asset/", html,
                "text/html", "utf-8", "")
        val avatarView = helper.getView<ImageView>(R.id.avatar)
        GlideApp.with(avatarView)
                .load(item.avatar)
                .placeholder(R.drawable.default_board_icon)
                .circleCrop()
                .into(avatarView)
    }
}