package xyz.loshine.nga.data.repository.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumBoardCategory

interface ForumRepository {

    fun getForumBoardCategories(): Flowable<List<ForumBoardCategory>>
}