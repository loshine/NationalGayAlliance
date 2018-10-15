package xzy.loshine.nga.ui.forum

import android.text.format.DateUtils

data class ForumTopicUiModel(
        val tid: Int = 0,
        val author: String = "",
        val replyCount: Int = 0,
        var lastPost: Long = 0,
        val parentVisible: Boolean = false,
        val parentName: String = "",
        val subject: String,
        val colorMask: Int,
        val bold: Boolean,
        val italic: Boolean,
        val underline: Boolean,
        val hasAttachment: Boolean = false,
        val locked: Boolean = false,
        val isAssemble: Boolean = false
) {

    companion object {
        // 颜色
        const val MASK_FONT_COLOR_DEFAULT = 0
        const val MASK_FONT_COLOR_RED = 1
        const val MASK_FONT_COLOR_BLUE = 2
        const val MASK_FONT_COLOR_GREEN = 4
        const val MASK_FONT_COLOR_ORANGE = 8
        const val MASK_FONT_COLOR_SILVER = 16

        const val MASK_FONT_STYLE_BOLD = 32
        const val MASK_FONT_STYLE_ITALIC = 64
        const val MASK_FONT_STYLE_UNDERLINE = 128

        const val MASK_TYPE_LOCK = 1024               // 主题被锁定 2^10
        const val MASK_TYPE_ATTACHMENT = 8192        // 主题中有附件 2^13
        const val MASK_TYPE_ASSEMBLE = 32768        // 合集 2^15
    }

    fun timeString(justNow: String): CharSequence = if (System.currentTimeMillis() - lastPost * 1000 < 1000 * 60) {
        justNow
    } else {
        DateUtils.getRelativeTimeSpanString(lastPost * 1000)
    }
}