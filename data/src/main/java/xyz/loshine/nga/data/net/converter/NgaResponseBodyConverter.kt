package xyz.loshine.nga.data.net.converter

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.orhanobut.logger.Logger
import okhttp3.ResponseBody
import retrofit2.Converter
import xyz.loshine.nga.data.exception.ServerException
import java.io.IOException
import java.nio.charset.Charset

internal class NgaResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        // gbk 编码
        var string = String(value.bytes(), Charset.forName("gbk"))
        // 直接制表符替换为 \t, \x 替换为 \\x
        string = string.replace("\t", "\\t")
                .replace("\\x", "\\\\x")
        val result = gson.fromJson(string, JsonObject::class.java)
        val messageElement = result.get("data").asJsonObject.get("__MESSAGE")
        Logger.d(string)
        if (messageElement != null) {
            // 如果发生错误，则抛出自定义错误
            throw ServerException(messageElement.asJsonObject.get("3").asInt, messageElement.asJsonObject.get("1").asString)
        } else {
            return adapter.fromJson(string)
        }
    }
}