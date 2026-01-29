package com.seekho.animeapp.data.repository

import android.content.Context
import com.seekho.animeapp.data.local.dao.AnimeDao
import com.seekho.animeapp.data.mapper.toDomain
import com.seekho.animeapp.data.mapper.toEntity
import com.seekho.animeapp.data.remote.AnimeApi

import com.seekho.animeapp.domain.model.Anime
import com.seekho.animeapp.domain.repository.AnimeRepository
import com.seekho.animeapp.util.NetworkResult
import com.seekho.animeapp.util.NetworkUtils

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val dao: AnimeDao,
    private val context: Context
) : AnimeRepository {

    override suspend fun getTopAnime(page: Int): NetworkResult<List<Anime>> {
        return try {
            if (NetworkUtils.isOnline(context)) {
                val response = api.getTopAnime(page)
                val anime = response.data.map { it.toDomain() }
                dao.insertAnime(anime.map { it.toEntity() })
                NetworkResult.Success(anime)
            } else {
                val cached = dao.getAllAnime().map { it.toDomain() }
                NetworkResult.Success(cached)
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun getAnimeDetail(id: Int): NetworkResult<Anime> {
        return try {
            val response = api.getAnimeDetails(id)
            NetworkResult.Success(response.data.toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to load details")
        }
    }
}
