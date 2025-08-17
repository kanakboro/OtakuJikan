package com.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.db.dao.AnimeDao
import com.core.db.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topAnimeDao(): AnimeDao
}