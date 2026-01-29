package com.seekho.animeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seekho.animeapp.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime")
    suspend fun getAllAnime(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(list: List<AnimeEntity>)
}
