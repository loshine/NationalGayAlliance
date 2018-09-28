package xzy.loshine.nga.ui.mybookmarkcategory

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped

@Module
interface MyBookmarkCategoryModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun myBookmarkCategoryFragment(): MyBookmarkCategoryFragment
}