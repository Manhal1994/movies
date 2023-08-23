package com.manhal.movies.persistence.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manhal.movies.models.SpokenLanguage

open class SpokenLanguageListConverter {
    @TypeConverter
    fun fromString(value: String): List<SpokenLanguage>? {
        val listType = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson<List<SpokenLanguage>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<SpokenLanguage>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}