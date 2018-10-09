package xzy.loshine.nga.ui.login

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xzy.loshine.nga.di.scopes.FragmentScoped

@Module
interface LoginModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun loginFragment(): LoginFragment
}