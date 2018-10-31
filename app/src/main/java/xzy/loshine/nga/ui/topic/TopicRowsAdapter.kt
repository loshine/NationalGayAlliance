package xzy.loshine.nga.ui.topic

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.ContentParser
import xzy.loshine.nga.utils.image.GlideApp
import xzy.loshine.nga.widget.ContentWebView
import javax.inject.Inject


class TopicRowsAdapter
@Inject constructor(private val parser: ContentParser) : BaseQuickAdapter<TopicRowUiModel, BaseViewHolder>(R.layout.recycler_item_topic_rows) {

    init {
        setHasStableIds(true)
    }

    override fun convert(helper: BaseViewHolder, item: TopicRowUiModel) {
        helper.setText(R.id.author, item.authorName)
                .setText(R.id.group, item.groupName)
                .setText(R.id.time, item.time)
                .setText(R.id.index, "#${item.index}")
        val contentWebView = helper.getView<ContentWebView>(R.id.content)
        val html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />${item.content}<script type=\"text/javascript\" src=\"file:///android_asset/collapse_script.js\"></script>"
        val list = parser.getImageListByHtml(item.content)
        contentWebView.setImageList(list)
        contentWebView.loadDataWithBaseURL("file:///android_asset/", html,
                "text/html", "utf-8", null)
        helper.getView<ImageView>(R.id.avatar).let {
            GlideApp.with(it)
                    .load(item.avatar)
                    .placeholder(R.drawable.default_board_icon)
                    .circleCrop()
                    .into(it)
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].pid.toLong()
    }
}