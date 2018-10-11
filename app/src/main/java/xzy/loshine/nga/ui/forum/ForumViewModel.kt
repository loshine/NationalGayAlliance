package xzy.loshine.nga.ui.forum

import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import xyz.loshine.nga.data.entity.Forum
import xyz.loshine.nga.data.entity.Topic
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xzy.loshine.nga.R
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class ForumViewModel
@Inject constructor(@Named("forum") private val forum: Forum,
                    private val forumRepository: ForumRepository) : BaseViewModel() {

    private var pageIndex = 1

    private val refreshingSubject = BehaviorSubject.createDefault(false)
    private val hasMoreSubject = BehaviorSubject.createDefault(false)
    private val isFavouriteSubject = BehaviorSubject.createDefault(false)

    fun refresh(): Flowable<List<Topic>> {
        pageIndex = 1
        return loadList().doOnSubscribe { refreshingSubject.onNext(true) }
                .doOnComplete { toast("刷新成功") }
                .doOnTerminate { refreshingSubject.onNext(false) }
    }

    fun loadMore(): Flowable<List<Topic>> {
        return loadList().doOnNext { toast("加载${it.size}个新帖子") }
    }

    fun isFavourite(): Flowable<Boolean> {
        return forumRepository.isFavourite(forum)
                .subscribeOn(schedulerProvider.io())
                .doOnNext { isFavouriteSubject.onNext(it) }
    }

    fun toggleFavourite(): Completable {
        return if (isFavouriteSubject.value == true) {
            removeFavourite()
        } else {
            addFavourite()
        }
    }

    fun getHasMore(): Flowable<Boolean> {
        return hasMoreSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getRefreshing(): Flowable<Boolean> {
        return refreshingSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getFavouriteDrawable(): Flowable<Int> {
        return isFavouriteSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
                .map { if (it) R.drawable.ic_heart else R.drawable.ic_heart_outline }
    }

    private fun loadList(): Flowable<List<Topic>> {
        return forumRepository.getForumPostList(forum.fid, pageIndex)
                .subscribeOn(schedulerProvider.io())
                .doOnNext { hasMoreSubject.onNext(it.topicList.isNotEmpty()) }
                .map { listData -> listData.topicList.map { Topic(it.value) } }
                .doOnComplete { pageIndex++ }
                .doOnError { toast(it.message ?: "") }
    }

    private fun addFavourite(): Completable {
        return forumRepository.addFavourite(forum)
                .subscribeOn(schedulerProvider.io())
                .doOnComplete { isFavouriteSubject.onNext(true) }
                .doOnComplete { toast("收藏成功") }
    }

    private fun removeFavourite(): Completable {
        return forumRepository.removeFavourite(forum)
                .subscribeOn(schedulerProvider.io())
                .doOnComplete { isFavouriteSubject.onNext(false) }
                .doOnComplete { toast("取消收藏成功") }
    }
}