package xzy.loshine.nga.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.MainActivity
import xzy.loshine.nga.ui.forum.ForumActivity
import xzy.loshine.nga.ui.forum.ForumModule
import xzy.loshine.nga.ui.forumgrouppager.ForumGroupPagerModule
import xzy.loshine.nga.ui.login.LoginActivity
import xzy.loshine.nga.ui.login.LoginModule
import xzy.loshine.nga.ui.topic.TopicActivity
import xzy.loshine.nga.ui.topic.TopicModule

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ForumGroupPagerModule::class])
    fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ForumModule::class])
    fun forumActivity(): ForumActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TopicModule::class])
    fun topicActivity(): TopicActivity
}