package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

data class Topic(
        val fid: Int = 0,
        val tpcurl: String = "",
        @SerializedName("quote_from")
        val quoteFrom: Int = 0,
        val author: String = "",
        val subject: String = "",
        @SerializedName("quote_to")
        val quoteTo: String = "",
        val icon: Int = 0,
        @SerializedName("postdate")
        val postDate: Long = 0,
        val recommend: Int = 0,
        @SerializedName("authorid") // 匿名时为 String，非匿名时为 Int
        val authorId: Any = 0,
        val type: Int = 0,
        val tid: Int = 0,
        val replies: Int = 0,
        @SerializedName("lastposter")
        val lastPoster: String = "",
        @SerializedName("lastpost")
        val lastPost: Long = 0,
        @SerializedName("topic_misc")
        val topicMisc: String = "",
        @SerializedName("lastmodify")
        val lastModify: Long = 0,
        val parent: TopicParent? = null
) {
    data class TopicParent(@SerializedName("2") val name: String)

    fun isAnonymous():Boolean {
        return authorId is String && authorId.startsWith("#anony_")
    }
}