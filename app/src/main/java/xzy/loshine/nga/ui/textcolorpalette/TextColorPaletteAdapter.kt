package xzy.loshine.nga.ui.textcolorpalette

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xzy.loshine.nga.R
import xzy.loshine.nga.utils.image.GlideApp
import javax.inject.Inject

class TextColorPaletteAdapter @Inject constructor(colorList: List<Pair<String, Int>>)
    : BaseQuickAdapter<Pair<String, Int>, BaseViewHolder>(R.layout.recycler_item_text_color_palette, colorList) {

    override fun convert(helper: BaseViewHolder, item: Pair<String, Int>) {
        val colorView = helper.getView<ImageView>(R.id.color)
        val colorDrawable = ContextCompat.getDrawable(mContext, R.drawable.drawable_text_color_preview)?.mutate()
        colorDrawable?.setTint(item.second)
        GlideApp.with(colorView)
                .load(colorDrawable?.constantState?.newDrawable())
                .circleCrop()
                .into(colorView)
        colorView.contentDescription = item.first
    }
}