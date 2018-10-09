package xyz.loshine.nga.data.repository.user

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.User

interface UserRepository {

    fun saveLoginCookies(cookies: String):Flowable<User>
}