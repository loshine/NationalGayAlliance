package xyz.loshine.nga.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorData(@SerializedName("__MESSAGE") val messageObject: MessageObject) {

    data class MessageObject(
            @SerializedName("1")
            val message: String,
            @SerializedName("3")
            val code: Int
    )
}