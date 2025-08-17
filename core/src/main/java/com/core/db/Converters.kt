package com.core.db

import androidx.room.TypeConverter
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class Converters {
    @TypeConverter
    fun fromJsonElement(json: JsonElement?): String? = json?.toString()

    @TypeConverter
    fun toJsonElement(json: String?): JsonElement? =
        json?.let { JsonParser.parseString(it) }
}