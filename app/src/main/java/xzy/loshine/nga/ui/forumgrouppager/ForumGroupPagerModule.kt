package xzy.loshine.nga.ui.forumgrouppager

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped
import xzy.loshine.nga.ui.mybookmarkgroup.MyBookmarkGroupModule


@Module(includes = [MyBookmarkGroupModule::class])
interface ForumGroupPagerModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun forumHomeFragment(): ForumGroupPagerFragment
}