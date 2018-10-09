package xzy.loshine.nga.ui.forum

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.di.scopes.FragmentScoped
import javax.inject.Named

@Module(includes = [ForumModule.FragmentModule::class])
class ForumModule {

    @Module
    interface FragmentModule {

        @FragmentScoped
        @ContributesAndroidInjector
        fun forumFragment(): ForumFragment
    }

    @ActivityScoped
    @Provides
    @Named("fid")
    fun fid(activity: ForumActivity): Int = activity.intent.getIntExtra("fid", 0)
}