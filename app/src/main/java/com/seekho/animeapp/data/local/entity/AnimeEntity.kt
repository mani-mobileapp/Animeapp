package com.seekho.animeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String,
    val episodes: Int,
    val rating: Double,
    val synopsis: String
)


