package xyz.loshine.nga.data.net.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xyz.loshine.nga.data.entity.AppApiResult
import xyz.loshine.nga.data.entity.SiteApiResult
import xyz.loshine.nga.data.entity.TopicDetailsData
import xyz.loshine.nga.data.entity.TopicListData

interface NgaApi {

    @GET("app_api.php?__lib=home&__act=category")
    fun getGroupList(): Flowable<AppApiResult<List<Any>>>

    @GET("thread.php?lite=js&noprefix")
    fun getTopicList(@Query("fid") fid: Int,
                     @Query("page") index: Int): Flowable<SiteApiResult<TopicListData>>

    @GET("read.php?lite=js&noprefix&v2")
    fun getTopicDetails(@Query("tid") tid: Int,
                        @Query("page") index: Int): Flowable<SiteApiResult<TopicDetailsData>>
}