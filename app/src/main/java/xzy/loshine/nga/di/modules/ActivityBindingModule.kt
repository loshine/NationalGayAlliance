package xzy.loshine.nga.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.MainActivity
import xzy.loshine.nga.ui.forum.ForumHomeModule
import xzy.loshine.nga.ui.mybookmarkcategory.MyBookmarkCategoryModule

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ForumHomeModule::class])
    fun mainActivity(): MainActivity
}