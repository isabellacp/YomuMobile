package com.isabellacp.dev.app.yomu.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.isabellacp.dev.app.yomu.dao.FavoritesDAO
import com.isabellacp.dev.app.yomu.models.Attributes
import com.isabellacp.dev.app.yomu.models.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class YomuDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDAO

    companion object {
        const val DATABASE_NAME = "yomu_database"
    }
}

class Converters {
    @TypeConverter
    fun fromString(value: String): Attributes {
        val type = object : TypeToken<Attributes>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromAttributes(attributes: Attributes): String {
        return Gson().toJson(attributes)
    }
}