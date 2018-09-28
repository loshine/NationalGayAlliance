package xyz.loshine.nga.data

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import xyz.loshine.nga.data.repository.forum.ForumDataRepository
import xyz.loshine.nga.data.repository.forum.ForumRepository
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
    fun forumRepository(forumDataRepository: ForumDataRepository): ForumRepository = forumDataRepository
}