package xyz.loshine.nga.data.entity

data class User(
        val uid: String,
        val cid: String,
        val nickname: String,
        val avatarUrl: String = "",
        val replyCount: Int = 0,
        val replyString: String = ""
)