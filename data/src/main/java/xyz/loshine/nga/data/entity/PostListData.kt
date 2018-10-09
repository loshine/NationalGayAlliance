package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

data class PostListData(
        @SerializedName("__GLOBAL")
        val global: Any,
        @SerializedName("__F")
        val forum: Any,
        @SerializedName("__ROWS")
        val rows: Int,
        @SerializedName("__T")
        val postList: Map<String, Map<String, Any>>,
        @SerializedName("__T__ROWS")
        val currentRows: Int, // 本页有多少条
        @SerializedName("__T__ROWS__PAGE")
        val postRows: Int,
        @SerializedName("__R__ROWS_PAGE")
        val rRows: Int
)