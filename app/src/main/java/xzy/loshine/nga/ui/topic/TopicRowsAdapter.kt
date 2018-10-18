package xzy.loshine.nga.ui.topic

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import xzy.loshine.nga.widget.ContentWebView
import javax.inject.Inject



class TopicRowsAdapter
@Inject constructor() : BaseQuickAdapter<TopicRowUiModel, BaseViewHolder>(R.layout.recycler_item_topic_rows) {

    override fun convert(helper: BaseViewHolder, item: TopicRowUiModel) {
        helper.setGone(R.id.subject, item.index == 0)
                .setText(R.id.subject, item.subject)
                .setText(R.id.author, item.authorName)
                .setText(R.id.group, item.groupName)
                .setText(R.id.time, item.time)
                .setText(R.id.index, "#${item.index}")
        val contentWebView = helper.getView<ContentWebView>(R.id.content)
        contentWebView.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" + item.content,
                "text/html", "utf-8", null)
        val avatarView = helper.getView<ImageView>(R.id.avatar)
        GlideApp.with(avatarView)
                .load(item.avatar)
                .placeholder(R.drawable.default_board_icon)
                .circleCrop()
                .into(avatarView)
    }
}