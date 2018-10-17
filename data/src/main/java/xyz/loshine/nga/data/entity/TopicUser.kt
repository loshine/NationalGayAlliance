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
            map["uid"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["username"].toString(),
            map["credit"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["medal"].toString(),
            map["reputation"].toString(),
            map["groupid"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["memberid"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["avatar"].toString(),
            map["yz"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["site"].toString(),
            map["honor"].toString(),
            map["regdate"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["mute_time"].toString(),
            map["postnum"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["rvrc"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["money"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["thisvisit"].toString().toDoubleOrNull()?.toInt() ?: 0,
            map["signature"].toString(),
            map["nickname"].toString(),
            map["bit_data"].toString().toDoubleOrNull()?.toInt() ?: 0
    )
}