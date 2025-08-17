package com.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonElement

@Entity(tableName = "top_anime")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topAnimeData: JsonElement
)
