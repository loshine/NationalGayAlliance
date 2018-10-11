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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import xyz.loshine.nga.data.net.api.NgaApi
import xyz.loshine.nga.data.net.converter.NgaConverterFactory
import xyz.loshine.nga.data.net.inteceptor.ReceivedCookiesInterceptor
import xyz.loshine.nga.data.repository.forum.ForumDataRepository
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xyz.loshine.nga.data.repository.topic.TopicDataRepository
import xyz.loshine.nga.data.repository.topic.TopicRepository
import xyz.loshine.nga.data.repository.user.UserDataRepository
import xyz.loshine.nga.data.repository.user.UserRepository
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
    fun okHttpClient(cache: Cache, interceptor: ReceivedCookiesInterceptor): OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BASIC })
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun converterFactory(gson: Gson): Converter.Factory = NgaConverterFactory.create(gson)

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
    fun ngaApi(retrofit: Retrofit): NgaApi = retrofit.create(NgaApi::class.java)

    @Singleton
    @Provides
    fun forumRepository(forumDataRepository: ForumDataRepository): ForumRepository = forumDataRepository

    @Singleton
    @Provides
    fun topicRepository(topicDataRepository: TopicDataRepository): TopicRepository = topicDataRepository

    @Singleton
    @Provides
    fun userRepository(userDataRepository: UserDataRepository): UserRepository = userDataRepository
}