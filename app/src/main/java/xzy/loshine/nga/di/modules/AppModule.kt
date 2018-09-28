package xzy.loshine.nga.di.modules

import dagger.Module
import dagger.Provides
import xzy.loshine.nga.utils.provider.resource.AndroidResourceProvider
import xzy.loshine.nga.utils.provider.resource.ResourceProvider
import xzy.loshine.nga.utils.provider.scheduler.AndroidSchedulerProvider
import xzy.loshine.nga.utils.provider.scheduler.SchedulerProvider
import javax.inject.Singleton

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * [AppCompont].
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun schedulerProvider(androidSchedulerProvider: AndroidSchedulerProvider): SchedulerProvider {
        return androidSchedulerProvider
    }

    @Singleton
    @Provides
    fun resourceProvider(androidResourceProvider: AndroidResourceProvider): ResourceProvider {
        return androidResourceProvider
    }
}