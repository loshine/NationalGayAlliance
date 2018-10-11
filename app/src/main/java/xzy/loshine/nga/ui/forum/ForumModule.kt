package xzy.loshine.nga.ui.forum

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import xyz.loshine.nga.data.entity.Forum
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
    @Named("forum")
    fun fid(activity: ForumActivity): Forum = activity.intent.getParcelableExtra("forum")
}