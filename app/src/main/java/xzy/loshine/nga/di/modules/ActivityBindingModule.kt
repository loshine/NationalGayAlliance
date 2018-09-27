package xzy.loshine.nga.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.ui.MainActivity
import xzy.loshine.nga.di.scopes.ActivityScoped

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    fun mainActivity(): MainActivity
}