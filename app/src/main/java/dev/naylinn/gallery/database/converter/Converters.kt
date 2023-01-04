package dev.naylinn.gallery.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.naylinn.gallery.database.model.CategoryEntity
import java.util.*

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    /**
     * For Category Entity
     */
    //CategoryEntity Converter
    @TypeConverter
    fun fromCategoryToJson(featureCorners: List<CategoryEntity>?): String {
        return featureCorners?.let { gson.toJson(it) } ?: ""
    }

    @TypeConverter
    fun fromJsonToCategory(json: String): List<CategoryEntity> {
        val type = object : TypeToken<List<CategoryEntity>>() {}.type
        return gson.fromJson(json, type)
    }

}