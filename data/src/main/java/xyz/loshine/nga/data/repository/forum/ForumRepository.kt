package xyz.loshine.nga.data.repository.forum

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.ForumGroup
import xyz.loshine.nga.data.entity.PostListData

interface ForumRepository {

    fun getForumBoardCategories(): Flowable<List<ForumGroup>>

    fun getForumPostList(fid: Int, index: Int): Flowable<PostListData>
}