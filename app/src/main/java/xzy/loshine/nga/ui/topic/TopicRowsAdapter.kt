package xzy.loshine.nga.ui.topic

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
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
                .setText(R.id.content, fromHtml(item.content))
        val avatarView = helper.getView<ImageView>(R.id.avatar)
        GlideApp.with(avatarView)
                .load(item.avatar)
                .placeholder(R.drawable.default_board_icon)
                .circleCrop()
                .into(avatarView)
    }

    @Suppress("DEPRECATION")
    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}