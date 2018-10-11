package xzy.loshine.nga.ui.mybookmarkforum

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped

@Module
interface MyBookmarkForumModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun myBookmarkCategoryFragment(): MyBookmarkForumFragment
}