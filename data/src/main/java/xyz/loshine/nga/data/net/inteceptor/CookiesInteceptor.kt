package xyz.loshine.nga.data.net.inteceptor

import io.paperdb.Paper
import okhttp3.Interceptor
import okhttp3.Response
import xyz.loshine.nga.data.entity.User
import xyz.loshine.nga.data.repository.user.UserDataRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 用户登录后需要添加 cookie 才可以使用 api
 */
@Singleton
class ReceivedCookiesInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val user = Paper.book().read<User>(UserDataRepository.KEY_USER)
        val builder = chain.request().newBuilder()
        if (user != null) {
            builder.addHeader("Cookie", "${UserDataRepository.TAG_UID}=${user.uid};${UserDataRepository.TAG_CID}=${user.cid}")
        }
        return chain.proceed(builder.build())
    }
}