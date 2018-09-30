package xzy.loshine.nga.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.MainActivity
import xzy.loshine.nga.ui.forum.ForumActivity
import xzy.loshine.nga.ui.forumgrouppager.ForumGroupPagerModule

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ForumGroupPagerModule::class])
    fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    fun boardActivity(): ForumActivity
}