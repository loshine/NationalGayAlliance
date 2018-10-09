package xzy.loshine.nga.ui.forum

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import xyz.loshine.nga.data.entity.Post
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class ForumViewModel
@Inject constructor(@Named("fid") private val fid: Int,
                    private val forumRepository: ForumRepository) : BaseViewModel() {

    private var pageIndex = 1

    private val refreshingSubject = BehaviorSubject.createDefault(false)
    private val hasMoreSubject = BehaviorSubject.createDefault(false)

    fun refresh(): Flowable<List<Post>> {
        pageIndex = 1
        return loadMore().doOnSubscribe { refreshingSubject.onNext(true) }
                .doOnTerminate { refreshingSubject.onNext(false) }
    }

    fun loadMore(): Flowable<List<Post>> {
        return forumRepository.getForumPostList(fid, pageIndex)
                .subscribeOn(schedulerProvider.io())
                .doOnNext { hasMoreSubject.onNext(it.postList.isNotEmpty()) }
                .map { listData -> listData.postList.map { Post(it.value) } }
                .doOnComplete { pageIndex++ }
                .doOnComplete { toast("刷新成功") }
                .doOnError { toast(it.message ?: "") }
    }

    fun getHasMore(): Flowable<Boolean> {
        return hasMoreSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getRefreshing(): Flowable<Boolean> {
        return refreshingSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }
}