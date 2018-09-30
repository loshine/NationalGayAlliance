package xyz.loshine.nga.data.entity

/**
 * Api 返回数据整体
 * {
 *    "data" : {},
 *    "encode": "gbk",
 *    "time": 1538302785
 * }
 */
data class SiteApiResult(
        val data: SiteApiResultData,
        val encode: String,
        val time: Long
)