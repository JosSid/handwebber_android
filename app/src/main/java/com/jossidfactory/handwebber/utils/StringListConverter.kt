package com.jossidfactory.handwebber.utils

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class StringListConverter {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        if (value == null) {
            return null
        }

        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)

        return adapter.fromJson(value)
    }

    @TypeConverter
    fun toString(value: List<String>?): String? {
        if (value == null) {
            return null
        }

        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)

        return adapter.toJson(value)
    }
}
