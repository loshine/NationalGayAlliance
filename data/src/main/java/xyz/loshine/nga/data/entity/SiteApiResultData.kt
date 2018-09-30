package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Api 返回数据中的 data
 */
data class SiteApiResultData(
        @SerializedName("__CU")
        val currentUser: Any,
        @SerializedName("__GLOBAL")
        val global: Any
)