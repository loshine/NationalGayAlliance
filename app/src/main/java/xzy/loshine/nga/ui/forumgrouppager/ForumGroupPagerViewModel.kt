package xzy.loshine.nga.ui.forumgrouppager

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumGroup
import xyz.loshine.nga.data.repository.forum.ForumRepository
import xzy.loshine.nga.di.scopes.ActivityScoped
import xzy.loshine.nga.ui.base.BaseViewModel
import javax.inject.Inject

@ActivityScoped
class ForumGroupPagerViewModel
@Inject constructor(private val forumRepository: ForumRepository) : BaseViewModel() {

    fun loadForumBoardCategories(): Flowable<List<ForumGroup>> {
        return forumRepository.getForumBoardCategories()
                .subscribeOn(schedulerProvider.io())
    }
}