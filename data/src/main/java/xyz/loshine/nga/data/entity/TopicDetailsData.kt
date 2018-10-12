package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

data class TopicDetailsData(
        @SerializedName("__CU")
        val currentUser: Any,
        @SerializedName("__GLOBAL")
        val global: Any,
        @SerializedName("__U")
        val userList: Map<String, Map<String, Any>>,
        @SerializedName("__T")
        val topic: TopicDetail,
        @SerializedName("__R")
        val rows: Map<String, TopicRow>
) {

    data class TopicDetail(
            val fid: Int = 0,
            val tid: Int = 0,
            val tpcurl: String = "",
            @SerializedName("quote_from") val quoteFrom: Int = 0,
            val author: String = "",
            val subject: String = "",
            @SerializedName("quote_to") val quoteTo: String = "",
            val icon: Int = 0,
            @SerializedName("postdate") val postDate: Long = 0,
            val locked: Int = 0,
            val recommend: Int = 0,
            val digest: Int = 0,
            val tpid: Int = 0,
            @SerializedName("authorid") val authorId: Int = 0,
            val type: Int = 0,
            val replies: Int = 0,
            @SerializedName("lastposter") val lastPoster: String = "",
            @SerializedName("lastpost") val lastPost: Long = 0,
            @SerializedName("topic_misc") val topicMisc: String = "",
            @SerializedName("lastmodify") val lastModify: Long = 0
    )

    data class TopicRow(
            val pid: Int = 0,
            val fid: Int = 0,
            val tid: Int = 0,
            val authorid: Int = 0,
            val type: Int = 0,
            val score: Int = 0,
            @SerializedName("score_2") val score2: Int = 0,
            val recommend: Int = 0,
            @SerializedName("postdate") val postDate: String = "",
            val subject: String = "",
            @SerializedName("alterinfo") val alterInfo: String = "",
            val content: String = "",
            @SerializedName("lou") val index: Int = 0,
            @SerializedName("content_length") val contentLength: Int = 0,
            @SerializedName("from_client") val fromClient: String? = null,
            @SerializedName("postdatetimestamp") val postDateTimestamp: Int = 0
    )
}