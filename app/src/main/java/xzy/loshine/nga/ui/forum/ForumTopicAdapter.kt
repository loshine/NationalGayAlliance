package xzy.loshine.nga.ui.forum

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.apache.commons.text.StringEscapeUtils
import xzy.loshine.nga.R
import xzy.loshine.nga.text.RoundBackgroundColorSpan
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_BLUE
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_GREEN
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_ORANGE
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_RED
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_SILVER
import javax.inject.Inject


class ForumTopicAdapter @Inject constructor() :
        BaseQuickAdapter<ForumTopicUiModel, BaseViewHolder>(R.layout.item_forum_topic) {

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

    override fun convert(helper: BaseViewHolder, item: ForumTopicUiModel) {
        helper.setText(R.id.author, item.author)
                .setText(R.id.comment_count, "${item.replyCount}")
                .setText(R.id.time, item.timeString(mContext.getString(R.string.just_now)))
                .setGone(R.id.parent_name, item.parentVisible)
                .setText(R.id.parent_name, item.parentName)

        val builder = SpannableStringBuilder(StringEscapeUtils.unescapeHtml4(item.subject))
        when (item.colorMask) {
            MASK_FONT_COLOR_RED -> colorRedSpan
            MASK_FONT_COLOR_BLUE -> colorBlueSpan
            MASK_FONT_COLOR_GREEN -> colorGreenSpan
            MASK_FONT_COLOR_ORANGE -> colorOrangeSpan
            MASK_FONT_COLOR_SILVER -> colorSilverSpan
            else -> null
        }?.let { builder.setSpan(it, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

        when {
            item.bold && item.italic -> styleBoldItalicSpan
            item.italic -> styleItalicSpan
            item.bold -> styleBoldSpan
            else -> null
        }?.let { builder.setSpan(it, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

        if (item.underline) {
            builder.setSpan(underlineSpan, 0, item.subject.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        if (item.hasAttachment) {
            val drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_attachment)!!
            drawable.setBounds(0, 0, ConvertUtils.dp2px(14f), ConvertUtils.dp2px(14f))
            val imageSpan = ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
            builder.append(" +")
            builder.setSpan(imageSpan, builder.length - 1, builder.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        if (item.isAssemble) {
            val typeString = mContext.getString(R.string.topic_tag_assemble)
            builder.append(typeString)
            builder.setSpan(colorAssembleSpan, builder.length - typeString.length, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (item.locked) {
            val typeString = mContext.getString(R.string.topic_tag_lock)
            builder.append(typeString)
            builder.setSpan(colorLockSpan, builder.length - typeString.length, builder.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        helper.setText(R.id.title, builder)
    }
}