package xzy.loshine.nga.ui.forumgroup

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xyz.loshine.nga.data.entity.Forum
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import javax.inject.Inject

class ForumGroupAdapter @Inject constructor() :
        BaseQuickAdapter<Forum, BaseViewHolder>(R.layout.recycler_item_forum_group) {

    override fun convert(helper: BaseViewHolder, item: Forum) {
        val image = helper.getView<ImageView>(R.id.image)
        GlideApp.with(image)
                .load("http://img4.nga.178.com/ngabbs/nga_classic/f/app/${item.fid}.png")
                .placeholder(R.drawable.default_board_icon)
                .dontAnimate()
                .into(image)
        helper.setText(R.id.text, item.name)
    }
}