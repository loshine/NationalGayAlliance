package xzy.loshine.nga.ui.forum

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xyz.loshine.nga.data.entity.Post
import xzy.loshine.nga.R
import javax.inject.Inject

class ForumPostAdapter @Inject constructor() :
        BaseQuickAdapter<Post, BaseViewHolder>(R.layout.item_forum_post) {

    override fun convert(helper: BaseViewHolder, item: Post) {
        helper.setText(R.id.title, item.subject)
                .setText(R.id.author, item.author)
    }
}