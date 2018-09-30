package xyz.loshine.nga.data.entity

data class AppApiResult<T>(
        val code: Int,
        val message: String,
        val result: T
)