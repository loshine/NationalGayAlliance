package xyz.loshine.nga.data.entity

/**
 * Api 返回数据整体
 * {
 *    "data" : {},
 *    "encode": "gbk",
 *    "time": 1538302785
 * }
 */
data class SiteApiResult<T>(
        val data: T,
        val encode: String,
        val time: Long
)