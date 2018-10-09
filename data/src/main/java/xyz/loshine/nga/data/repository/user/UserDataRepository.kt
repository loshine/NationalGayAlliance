package xyz.loshine.nga.data.repository.user

import android.text.TextUtils
import io.paperdb.Paper
import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.User
import xyz.loshine.nga.data.exception.CookiesParseException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor() : UserRepository {

    companion object {
        private const val TAG_UID = "ngaPassportUid"
        private const val TAG_CID = "ngaPassportCid"
        private const val TAG_USER_NAME = "ngaPassportUrlencodedUname"

        private const val KEY_USER = "USER"
    }

    override fun saveLoginCookies(cookies: String): Flowable<User> {
        if (cookies.contains(TAG_UID)) {
            var uid: String? = null
            var cid: String? = null
            var userName: String? = null
            for (c in cookies.split(";")) {
                val cookie = c.trim()
                when {
                    cookie.contains(TAG_UID) -> uid = cookie.substring(TAG_UID.length + 1)
                    cookie.contains(TAG_CID) -> cid = cookie.substring(TAG_CID.length + 1)
                    cookie.contains(TAG_USER_NAME) -> {
                        userName = cookie.substring(TAG_USER_NAME.length + 1)
                        try {
                            userName = URLDecoder.decode(userName, "gbk")
                            userName = URLDecoder.decode(userName, "gbk")
                        } catch (e: UnsupportedEncodingException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            if (!TextUtils.isEmpty(cid) && !TextUtils.isEmpty(uid) && !TextUtils.isEmpty(userName)) {
                val user = User(uid!!, cid!!, userName!!)
                Paper.book().write(KEY_USER, user)
                return Flowable.just(user)
            }
        }
        return Flowable.error(CookiesParseException("cookies parse error: cookies = $cookies"))
    }

}