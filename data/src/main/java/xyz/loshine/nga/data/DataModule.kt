package xyz.loshine.nga.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.loshine.nga.data.repository.forum.ForumDataRepository
import xyz.loshine.nga.data.repository.forum.ForumRepository
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [DataModule.ContextProviderModule::class])
class DataModule {

    @Module
    interface ContextProviderModule {
        // expose Application as an injectable context
        @Binds
        fun bindContext(application: Application): Context
    }

    companion object {
        const val RESPONSE_CACHE_FILE = "response_cache"
        const val RESPONSE_CACHE_SIZE = 10 * 1024L * 1024L

        const val HTTP_CONNECT_TIMEOUT = 10L
        const val HTTP_READ_TIMEOUT = 10L
        const val HTTP_WRITE_TIMEOUT = 10L
    }

    @Singleton
    @Provides
    fun cacheDir(context: Context): File = File(context.cacheDir, RESPONSE_CACHE_FILE)

    @Singleton
    @Provides
    fun cache(cacheDir: File): Cache = Cache(cacheDir, RESPONSE_CACHE_SIZE)

    @Singleton
    @Provides
    fun okHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun converterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun callAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://bbs.nga.cn/")
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
    }

    @Singleton
    @Provides
    fun forumRepository(forumDataRepository: ForumDataRepository): ForumRepository = forumDataRepository
}