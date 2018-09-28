package xzy.loshine.nga.ui.forum

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped
import xzy.loshine.nga.ui.mybookmarkcategory.MyBookmarkCategoryModule


@Module(includes = [MyBookmarkCategoryModule::class])
interface ForumHomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun forumHomeFragment(): ForumHomeFragment
}