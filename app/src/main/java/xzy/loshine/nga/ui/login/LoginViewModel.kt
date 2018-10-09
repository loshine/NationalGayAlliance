package xzy.loshine.nga.ui.login

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.User
import xyz.loshine.nga.data.repository.user.UserRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject

@ActivityScoped
class LoginViewModel
@Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {

    fun saveCookies(cookies: String): Flowable<User> {
        return userRepository.saveLoginCookies(cookies)
                .subscribeOn(schedulerProvider.io())
                .doOnComplete { toast("登陆成功") }
    }
}