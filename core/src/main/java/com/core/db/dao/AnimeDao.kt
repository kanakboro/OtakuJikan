package com.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.core.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertTopAnime(animeEntity: AnimeEntity)

    @Update
    suspend fun updateTopAnime(animeEntity: AnimeEntity)

    @Query("SELECT * FROM top_anime")
    fun getTopAnime(): Flow<List<AnimeEntity>>
}