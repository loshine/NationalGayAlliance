package xzy.loshine.nga.ui.reply

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped
import xzy.loshine.nga.ui.textcolorpalette.TextColorPaletteModule

@Module(includes = [ReplyModule.FragmentModule::class, TextColorPaletteModule::class])
class ReplyModule {

    @Module
    interface FragmentModule {

        @FragmentScoped
        @ContributesAndroidInjector
        fun replyFragment(): ReplyFragment
    }
}