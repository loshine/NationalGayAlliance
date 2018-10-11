package xyz.loshine.nga.data.repository.topic

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.TopicDetailsData

interface TopicRepository {

    fun getTopicList(tid: Int, index: Int): Flowable<TopicDetailsData>
}