package xyz.loshine.nga.data

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import xyz.loshine.nga.data.repository.forum.ForumDataRepository
import xyz.loshine.nga.data.repository.forum.ForumRepository
import java.io.File
import javax.inject.Singleton


@Module(includes = [DataModule.ContextProviderModule::class])
class DataModule {

    @Module
    interface ContextProviderModule {
        // expose Application as an injectable context
        @Binds
        fun bindContext(application: Application): Context
    }

    @Singleton
    @Provides
    fun cacheDir(context: Context): File = context.cacheDir

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun forumRepository(forumDataRepository: ForumDataRepository): ForumRepository = forumDataRepository
}