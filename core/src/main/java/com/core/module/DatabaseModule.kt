package com.core.module

import android.content.Context
import androidx.room.Room
import com.core.db.AppDatabase
import com.core.db.dao.AnimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "anime_db").build()
    }

    @Provides
    fun provideAnimeDao(db: AppDatabase): AnimeDao = db.topAnimeDao()
}