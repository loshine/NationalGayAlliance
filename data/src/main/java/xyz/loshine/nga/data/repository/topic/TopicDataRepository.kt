package xyz.loshine.nga.data.repository.topic

import io.reactivex.Flowable
import xyz.loshine.nga.data.entity.SiteApiResult
import xyz.loshine.nga.data.entity.TopicDetailsData
import xyz.loshine.nga.data.net.api.NgaApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicDataRepository @Inject constructor(private val ngaApi: NgaApi) : TopicRepository {

    override fun getTopicList(tid: Int, index: Int): Flowable<TopicDetailsData> {
        return ngaApi.getTopicDetails(tid, index).map { it.data }
    }

}