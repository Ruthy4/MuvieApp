package com.example.muvies.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.buffer
import okio.source
import java.lang.reflect.Type

object TestUtil {

    fun <T> parseJsonFileToObject(fileName: String, type: Type): T? {
        val json = getJsonStringFromFile(fileName)
        val adapter: JsonAdapter<T> = Moshi.Builder().build().adapter(type)
        return adapter.fromJson(json)
    }
    fun getJsonStringFromFile(fileName: String): String {
        val inputStream = this::class.java.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream!!.source().buffer()
        return source.readString(Charsets.UTF_8)
    }
}
