package xzy.loshine.nga.text

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import com.blankj.utilcode.util.ConvertUtils

class RoundBackgroundColorSpan(private val backgroundColor: Int,
                               private val textColor: Int,
                               private val textSize: Float? = null) : ReplacementSpan() {

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return paint.measureText(text, start, end).toInt() + ConvertUtils.dp2px(8f)
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val originalColor = paint.color
        paint.color = backgroundColor
        textSize?.let { paint.textSize = textSize }
        val rect = RectF(x + ConvertUtils.dp2px(4f),
                (top + ConvertUtils.dp2px(3f)).toFloat(),
                x + (paint.measureText(text, start, end) + ConvertUtils.dp2px(12f)),
                (bottom - ConvertUtils.dp2px(1f)).toFloat())
        canvas.drawRoundRect(rect, ConvertUtils.dp2px(4f).toFloat(), ConvertUtils.dp2px(4f).toFloat(), paint)
        paint.color = textColor
        // 画文字,两边各增加8dp
        text?.let { canvas.drawText(it, start, end, x + ConvertUtils.dp2px(8f), (y - ConvertUtils.dp2px(1f)).toFloat(), paint) }
        //将paint复原
        paint.color = originalColor
    }

}