package com.seekho.animeapp.domain.repository


import com.seekho.animeapp.domain.model.Anime
import com.seekho.animeapp.util.NetworkResult

interface AnimeRepository {
    suspend fun getTopAnime(page: Int): NetworkResult<List<Anime>>
    suspend fun getAnimeDetail(id: Int): NetworkResult<Anime>
}
