package xzy.loshine.nga.ui.mybookmarkforum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.Forum
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject

@ActivityScoped
class MyBookmarkForumViewModel
@Inject constructor(private val forumRepository: ForumRepository) : BaseViewModel() {

    fun load():Flowable<List<Forum>> {
        return forumRepository.getAllFavourites()
                .subscribeOn(schedulerProvider.io())
    }
}