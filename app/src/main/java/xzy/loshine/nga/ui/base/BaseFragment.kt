package xzy.loshine.nga.ui.base

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import xzy.loshine.nga.utils.provider.scheduler.SchedulerProvider
import javax.inject.Inject

abstract class BaseFragment(layoutResId: Int? = null) : EasyFragment(layoutResId), ArchView {

    @Inject
    lateinit var schedulerProvider: SchedulerProvider

    private val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun unBindViewModel() {
        compositeDisposable.dispose()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}