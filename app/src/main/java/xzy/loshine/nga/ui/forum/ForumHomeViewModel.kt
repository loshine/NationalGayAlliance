package xzy.loshine.nga.ui.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumBoardCategory
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject

@ActivityScoped
class ForumHomeViewModel
@Inject constructor(private val forumRepository: ForumRepository) : BaseViewModel() {

    fun loadForumBoardCategories(): Flowable<List<ForumBoardCategory>> {
        return forumRepository.getForumBoardCategories()
                .subscribeOn(schedulerProvider.io())
    }
}