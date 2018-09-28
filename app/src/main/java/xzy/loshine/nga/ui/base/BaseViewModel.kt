package xzy.loshine.nga.ui.base

import androidx.annotation.StringRes
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import xzy.loshine.nga.utils.provider.resource.ResourceProvider
import xzy.loshine.nga.utils.provider.scheduler.SchedulerProvider
import javax.inject.Inject

abstract class BaseViewModel {

    @Inject
    lateinit var schedulerProvider: SchedulerProvider
    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val messageSubject by lazy { PublishSubject.create<String>() }

    fun getMessage(): Flowable<String> {
        return messageSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun toast(message: String) {
        messageSubject.onNext(message)
    }

    fun toast(@StringRes resId: Int) {
        messageSubject.onNext(resourceProvider.getString(resId))
    }

    fun toast(@StringRes resId: Int, vararg args: Any) {
        messageSubject.onNext(resourceProvider.getString(resId, args))
    }
}