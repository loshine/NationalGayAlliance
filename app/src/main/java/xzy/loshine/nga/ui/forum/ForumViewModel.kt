package xzy.loshine.nga.ui.forum

import android.text.TextUtils
import android.util.Base64
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
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_BLUE
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_DEFAULT
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_GREEN
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_ORANGE
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_RED
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_COLOR_SILVER
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_STYLE_BOLD
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_STYLE_ITALIC
import xzy.loshine.nga.ui.forum.ForumTopicUiModel.Companion.MASK_FONT_STYLE_UNDERLINE
import javax.inject.Inject
import javax.inject.Named

@ActivityScoped
class ForumViewModel
@Inject constructor(@Named("forum") private val forum: Forum,
                    private val forumRepository: ForumRepository) : BaseViewModel() {

    private var pageIndex = 1

    private val refreshingSubject = BehaviorSubject.createDefault(false)
    private val isFavouriteSubject = BehaviorSubject.createDefault(false)

    fun refresh(): Flowable<List<ForumTopicUiModel>> {
        pageIndex = 1
        return loadList().doOnSubscribe { refreshingSubject.onNext(true) }
                .doOnComplete { toast("刷新成功") }
                .doOnTerminate { refreshingSubject.onNext(false) }
    }

    fun loadMore(): Flowable<List<ForumTopicUiModel>> {
        return loadList().doOnNext { toast("已加载${it.size}个新帖子") }
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

    fun getRefreshing(): Flowable<Boolean> {
        return refreshingSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
    }

    fun getFavouriteDrawable(): Flowable<Int> {
        return isFavouriteSubject.toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(schedulerProvider.computation())
                .map { if (it) R.drawable.ic_heart else R.drawable.ic_heart_outline }
    }

    private fun loadList(): Flowable<List<ForumTopicUiModel>> {
        return forumRepository.getForumPostList(forum.fid, pageIndex)
                .subscribeOn(schedulerProvider.io())
                .map { listData -> listData.topicList.map { convertItemUiModel(it.value) } }
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

    private fun convertItemUiModel(topic: Topic): ForumTopicUiModel {
        var colorMask: Int = MASK_FONT_COLOR_DEFAULT
        var bold = false
        var italic = false
        var underline = false

        if (!TextUtils.isEmpty(topic.topicMisc)) {
            val bytes = Base64.decode(topic.topicMisc, Base64.DEFAULT)
            if (bytes != null && bytes.isNotEmpty() && bytes[0].toInt() == 1) {
                val lastBytes = bytes[bytes.lastIndex].toInt()
                colorMask = when {
                    (lastBytes and MASK_FONT_COLOR_RED) == MASK_FONT_COLOR_RED -> MASK_FONT_COLOR_RED
                    (lastBytes and MASK_FONT_COLOR_BLUE) == MASK_FONT_COLOR_BLUE -> MASK_FONT_COLOR_BLUE
                    (lastBytes and MASK_FONT_COLOR_GREEN) == MASK_FONT_COLOR_GREEN -> MASK_FONT_COLOR_GREEN
                    (lastBytes and MASK_FONT_COLOR_ORANGE) == MASK_FONT_COLOR_ORANGE -> MASK_FONT_COLOR_ORANGE
                    (lastBytes and MASK_FONT_COLOR_SILVER) == MASK_FONT_COLOR_SILVER -> MASK_FONT_COLOR_SILVER
                    else -> MASK_FONT_COLOR_DEFAULT
                }
                bold = lastBytes and MASK_FONT_STYLE_BOLD == MASK_FONT_STYLE_BOLD
                italic = lastBytes and MASK_FONT_STYLE_ITALIC == MASK_FONT_STYLE_ITALIC
                underline = lastBytes and MASK_FONT_STYLE_UNDERLINE == MASK_FONT_STYLE_UNDERLINE
            }
        }

        return ForumTopicUiModel(
                topic.tid,
                topic.author,
                topic.replies,
                topic.lastPost,
                topic.parent != null,
                topic.parent?.name ?: "",
                topic.subject,
                colorMask,
                bold,
                italic,
                underline,
                topic.type and ForumTopicUiModel.MASK_TYPE_ATTACHMENT == ForumTopicUiModel.MASK_TYPE_ATTACHMENT,
                topic.type and ForumTopicUiModel.MASK_TYPE_LOCK == ForumTopicUiModel.MASK_TYPE_LOCK,
                topic.type and ForumTopicUiModel.MASK_TYPE_ASSEMBLE == ForumTopicUiModel.MASK_TYPE_ASSEMBLE
        )
    }
}