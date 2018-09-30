package xzy.loshine.nga.ui.mybookmarkgroup

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped

@Module
interface MyBookmarkGroupModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun myBookmarkCategoryFragment(): MyBookmarkGroupFragment
}