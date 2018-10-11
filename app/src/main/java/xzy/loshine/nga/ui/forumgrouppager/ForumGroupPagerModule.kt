package xzy.loshine.nga.ui.forumgrouppager

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped
import xzy.loshine.nga.ui.mybookmarkforum.MyBookmarkForumModule


@Module(includes = [MyBookmarkForumModule::class])
interface ForumGroupPagerModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun forumHomeFragment(): ForumGroupPagerFragment
}