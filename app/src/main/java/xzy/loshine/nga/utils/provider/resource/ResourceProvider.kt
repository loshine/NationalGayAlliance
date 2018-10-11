package xzy.loshine.nga.utils.provider.resource

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getStringArray(@ArrayRes resId: Int): Array<String>

    fun getDrawable(@DrawableRes resId: Int): Drawable
}
