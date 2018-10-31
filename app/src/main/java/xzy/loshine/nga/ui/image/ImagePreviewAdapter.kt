package xzy.loshine.nga.ui.image

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import javax.inject.Inject
import javax.inject.Named

class ImagePreviewAdapter
@Inject constructor(@Named("image_list") private val imageList: List<String>)
    : BaseQuickAdapter<String, BaseViewHolder>(R.layout.recycler_item_image_preview, imageList) {

    override fun convert(helper: BaseViewHolder, item: String) {
        val imageView = helper.getView<ImageView>(R.id.image)
        GlideApp.with(imageView)
                .load(item)
                .fitCenter()
                .into(imageView)
    }
}