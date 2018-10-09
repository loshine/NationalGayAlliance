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
        val postDate: Int = 0,
        val recommend: Int = 0,
        @SerializedName("authorid")
        val authorId: Int = 0,
        val type: Int = 0,
        val tid: Int = 0,
        val replies: Int = 0,
        @SerializedName("lastposter")
        val lastPoster: String = "",
        @SerializedName("lastpost")
        val lastPost: Int = 0,
        @SerializedName("topic_misc")
        val topicMisc: String = "",
        @SerializedName("lastmodify")
        val lastModify: Int = 0
) {
    constructor(map: Map<String, Any>) : this(
            map["fid"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["tpcurl"]?.toString() ?: "",
            map["quote_from"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["author"]?.toString() ?: "",
            map["subject"]?.toString() ?: "",
            map["quote_to"]?.toString() ?: "",
            map["icon"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["postdate"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["recommend"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["authorid"]?.toString()?.toDoubleOrNull()?.toInt() ?: 0,
            map["type"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["tid"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["replies"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["lastposter"]?.toString() ?: "",
            map["lastpost"]?.toString()?.toDouble()?.toInt() ?: 0,
            map["topic_misc"]?.toString() ?: "",
            map["lastmodify"]?.toString()?.toDouble()?.toInt() ?: 0
    )
}