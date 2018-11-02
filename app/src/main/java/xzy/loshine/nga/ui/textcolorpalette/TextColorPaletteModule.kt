package xzy.loshine.nga.ui.textcolorpalette

import android.graphics.Color
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped

@Module(includes = [TextColorPaletteModule.FragmentModule::class])
class TextColorPaletteModule {

    @Module
    interface FragmentModule {
        @FragmentScoped
        @ContributesAndroidInjector
        fun textColorPaletteDialogFragment(): TextColorPaletteDialogFragment
    }

    @Provides
    fun colorMap(): List<Pair<String, Int>> {
        return listOf(
                "limegreen" to Color.parseColor("#32cd32"),
                "seegreen" to Color.parseColor("#2e8b57"),
                "green" to Color.parseColor("#008000"),
                "teal" to Color.parseColor("#008080"),
                "darkblue" to Color.parseColor("#00008b"),
                "blue" to Color.parseColor("#0000ff"),
                "royalblue" to Color.parseColor("#4169e1"),
                "skyblue" to Color.parseColor("#87ceeb"),
                "purple" to Color.parseColor("#800080"),
                "indigo" to Color.parseColor("#4b0082"),
                "deeppink" to Color.parseColor("#ff1493"),
                "orange" to Color.parseColor("#ffa500"),
                "orangered" to Color.parseColor("#ff4500"),
                "crimson" to Color.parseColor("#dc143c"),
                "red" to Color.parseColor("#ff0000"),
                "firebrick" to Color.parseColor("#b22222"),
                "darkred" to Color.parseColor("#8b0000"),
                "tomato" to Color.parseColor("#ff6347"),
                "coral" to Color.parseColor("#ff7f50"),
                "burlywood" to Color.parseColor("#deb887"),
                "sandybrown" to Color.parseColor("#f4a460"),
                "sienna" to Color.parseColor("#a0522d"),
                "chocolate" to Color.parseColor("#d2691e"),
                "silver" to Color.parseColor("#c0c0c0")
        )
    }
}