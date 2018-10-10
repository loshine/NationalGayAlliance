package xzy.loshine.nga.ui.forum

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Base64
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.apache.commons.text.StringEscapeUtils
import org.ocpsoft.prettytime.PrettyTime
import xyz.loshine.nga.data.entity.Topic
import xzy.loshine.nga.R
import xzy.loshine.nga.text.RoundBackgroundColorSpan
import java.util.*
import javax.inject.Inject


class ForumPostAdapter @Inject constructor() :
        BaseQuickAdapter<Topic, BaseViewHolder>(R.layout.item_forum_post) {

    companion object {
        // 颜色和格式，后 8 位
        const val MASK_FONT_COLOR_RED = 1 //
        const val MASK_FONT_COLOR_BLUE = 2
        const val MASK_FONT_COLOR_GREEN = 4
        const val MASK_FONT_COLOR_ORANGE = 8
        const val MASK_FONT_COLOR_SILVER = 16

        const val MASK_FONT_STYLE_BOLD = 32
        const val MASK_FONT_STYLE_ITALIC = 64
        const val MASK_FONT_STYLE_BOLD_ITALIC = 96
        const val MASK_FONT_STYLE_UNDERLINE = 128

        const val MASK_TYPE_LOCK = 1024               // 主题被锁定 2^10
        const val MASK_TYPE_ATTACHMENT = 8192        // 主题中有附件 2^13
        const val MASK_TYPE_ASSEMBLE = 32768        // 合集 2^15
    }

    private val prettyTime = PrettyTime()

    private val colorRedSpan by lazy { ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.title_red)) }
    private val colorBlueSpan by lazy { ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.title_blue)) }
    private val colorGreenSpan by lazy { ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.title_green)) }
    private val colorOrangeSpan by lazy { ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.title_orange)) }
    private val colorSilverSpan by lazy { ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.title_silver)) }

    private val styleBoldItalicSpan by lazy { StyleSpan(Typeface.BOLD_ITALIC) }
    private val styleItalicSpan by lazy { StyleSpan(Typeface.ITALIC) }
    private val styleBoldSpan by lazy { StyleSpan(Typeface.BOLD) }

    private val underlineSpan by lazy { UnderlineSpan() }

    private val colorLockSpan by lazy { RoundBackgroundColorSpan(ContextCompat.getColor(mContext, R.color.type_lock), Color.WHITE, ConvertUtils.sp2px(12f).toFloat()) }
    private val colorAssembleSpan by lazy { RoundBackgroundColorSpan(ContextCompat.getColor(mContext, R.color.type_assemble), Color.WHITE, ConvertUtils.sp2px(12f).toFloat()) }

    override fun convert(helper: BaseViewHolder, item: Topic) {
        helper.setText(R.id.author, item.author)
                .setText(R.id.comment_count, "${item.replies}")
                .setText(R.id.time, prettyTime.formatUnrounded(Date(item.lastPost * 1000)))
                .setGone(R.id.parent_name, item.parent != null)
                .setText(R.id.parent_name, "[${item.parent?.name}]")

        val builder = SpannableStringBuilder(StringEscapeUtils.unescapeHtml4(item.subject))
        if (!TextUtils.isEmpty(item.topicMisc)) {
            val bytes = Base64.decode(item.topicMisc, Base64.DEFAULT)
            if (bytes != null) {
                if (bytes[0].toInt() == 1) {
                    // 颜色
                    when {
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_COLOR_RED) == MASK_FONT_COLOR_RED -> colorRedSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_COLOR_BLUE) == MASK_FONT_COLOR_BLUE -> colorBlueSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_COLOR_GREEN) == MASK_FONT_COLOR_GREEN -> colorGreenSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_COLOR_ORANGE) == MASK_FONT_COLOR_ORANGE -> colorOrangeSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_COLOR_SILVER) == MASK_FONT_COLOR_SILVER -> colorSilverSpan
                        else -> null
                    }?.let { builder.setSpan(it, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
                    // 样式
                    when {
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_STYLE_BOLD_ITALIC) == MASK_FONT_STYLE_BOLD_ITALIC -> styleBoldItalicSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_STYLE_ITALIC) == MASK_FONT_STYLE_ITALIC -> styleItalicSpan
                        (bytes[bytes.lastIndex].toInt() and MASK_FONT_STYLE_BOLD) == MASK_FONT_STYLE_BOLD -> styleBoldSpan
                        else -> null
                    }?.let { builder.setSpan(it, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
                    // 下划线
                    if (bytes[bytes.lastIndex].toInt() and MASK_FONT_STYLE_UNDERLINE == MASK_FONT_STYLE_UNDERLINE) {
                        builder.setSpan(underlineSpan, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }
            }
        }

        if (item.type and MASK_TYPE_ATTACHMENT == MASK_TYPE_ATTACHMENT) {
            val drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_attachment)!!
            drawable.setBounds(0, 0, ConvertUtils.dp2px(14f), ConvertUtils.dp2px(14f))
            val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
            builder.append(" +")
            builder.setSpan(imageSpan, builder.length - 1, builder.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        if (item.type and MASK_TYPE_ASSEMBLE == MASK_TYPE_ASSEMBLE) {
            val typeString = "合集"
            builder.append(typeString)
            builder.setSpan(colorAssembleSpan, builder.length - typeString.length, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (item.type and MASK_TYPE_LOCK == MASK_TYPE_LOCK) {
            val typeString = "锁定"
            builder.append(typeString)
            builder.setSpan(colorLockSpan, builder.length - typeString.length, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        helper.setText(R.id.title, builder)
    }
}