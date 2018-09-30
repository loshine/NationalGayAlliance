package xyz.loshine.nga.data.repository.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumGroup

interface ForumRepository {

    fun getForumBoardCategories(): Flowable<List<ForumGroup>>
}