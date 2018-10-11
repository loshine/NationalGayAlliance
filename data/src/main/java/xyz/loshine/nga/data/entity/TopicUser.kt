package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName


data class TopicUser(
        val uid: Int = 0,
        val username: String = "",
        val credit: Int = 0,
        val medal: String = "",
        val reputation: String = "",
        @SerializedName("groupid") val groupId: Int = 0,
        @SerializedName("memberid") val memberId: Int = 0,
        val avatar: String = "",
        @SerializedName("yz") val yz: Int = 0,
        val site: String = "",
        val honor: String = "",
        @SerializedName("regdate") val registerDate: Int = 0,
        @SerializedName("mute_time") val muteTime: String = "",
        @SerializedName("postnum") val postNum: Int = 0,
        @SerializedName("rvrc") val rvrc: Int = 0,
        val money: Int = 0,
        @SerializedName("thisvisit") val thisVisit: Int = 0,
        val signature: String = "",
        val nickname: String = "",
        @SerializedName("bit_data") val bitData: Int = 0
) {
    constructor(map: Map<String, Any>) : this(
            map["uid"].toString().toDouble().toInt(),
            map["username"].toString(),
            map["credit"].toString().toDouble().toInt(),
            map["medal"].toString(),
            map["reputation"].toString(),
            map["groupid"].toString().toDouble().toInt(),
            map["memberid"].toString().toDouble().toInt(),
            map["avatar"].toString(),
            map["yz"].toString().toDouble().toInt(),
            map["site"].toString(),
            map["honor"].toString(),
            map["regdate"].toString().toDouble().toInt(),
            map["mute_time"].toString(),
            map["postnum"].toString().toDouble().toInt(),
            map["rvrc"].toString().toDouble().toInt(),
            map["money"].toString().toDouble().toInt(),
            map["thisvisit"].toString().toDouble().toInt(),
            map["signature"].toString(),
            map["nickname"].toString(),
            map["bit_data"].toString().toDouble().toInt()
    )
}