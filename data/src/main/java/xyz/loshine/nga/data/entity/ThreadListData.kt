package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

data class ThreadListData(
        @SerializedName("__GLOBAL")
        val global: Any,
        @SerializedName("__F")
        val forum: Any,
        @SerializedName("__ROWS")
        val rows: Long,
        @SerializedName("__T")
        val threadList: List<Map<String, Any>>,
        @SerializedName("__T__ROWS")
        val currentRows: Long, // 本页有多少条
        @SerializedName("__T__ROWS__PAGE")
        val threadRows: Long,
        @SerializedName("__R__ROWS_PAGE")
        val rRows: Long
)