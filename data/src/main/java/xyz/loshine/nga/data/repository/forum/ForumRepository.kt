package xyz.loshine.nga.data.repository.forum

import io.reactivex.Completable
import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.Forum
import xyz.loshine.nga.data.entity.ForumGroup
import xyz.loshine.nga.data.entity.TopicListData

interface ForumRepository {

    fun getForumBoardCategories(): Flowable<List<ForumGroup>>

    fun getForumPostList(fid: Int, index: Int): Flowable<TopicListData>

    fun addFavourite(forum: Forum): Completable

    fun removeFavourite(forum: Forum): Completable

    fun getAllFavourites(): Flowable<List<Forum>>

    fun isFavourite(forum: Forum): Flowable<Boolean>
}