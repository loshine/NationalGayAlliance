package xzy.loshine.nga.ui.forum

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.apache.commons.text.StringEscapeUtils
import xyz.loshine.nga.data.entity.Topic
import xzy.loshine.nga.R
import javax.inject.Inject

class ForumPostAdapter @Inject constructor() :
        BaseQuickAdapter<Topic, BaseViewHolder>(R.layout.item_forum_post) {

    override fun convert(helper: BaseViewHolder, item: Topic) {
        helper.setText(R.id.title, StringEscapeUtils.unescapeHtml4(item.subject))
                .setText(R.id.author, item.author)
    }
}