package xzy.loshine.nga.ui.forumcategory

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xyz.loshine.nga.data.entity.ForumBoard
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import javax.inject.Inject

class ForumCategoryBoardAdapter @Inject constructor() :
        BaseQuickAdapter<ForumBoard, BaseViewHolder>(R.layout.recycler_item_forum_category_board) {

    override fun convert(helper: BaseViewHolder, item: ForumBoard) {
        val image = helper.getView<ImageView>(R.id.image)
        GlideApp.with(image)
                .load("http://img4.nga.178.com/ngabbs/nga_classic/f/app/${item.fid}.png")
                .placeholder(R.drawable.default_board_icon)
                .dontAnimate()
                .into(image)
        helper.setText(R.id.text, item.name)
    }

}