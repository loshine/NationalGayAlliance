package xzy.loshine.nga.ui.topic

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.di.scopes.FragmentScoped
import javax.inject.Named

@Module(includes = [TopicModule.FragmentModule::class])
class TopicModule {

    @Module
    interface FragmentModule {

        @FragmentScoped
        @ContributesAndroidInjector
        fun topicFragment(): TopicFragment
    }

    @Named("tid")
    @ActivityScoped
    @Provides
    fun tid(activity: TopicActivity): Int = activity.intent.getIntExtra("tid", 0)
}

