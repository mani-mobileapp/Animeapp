package com.seekho.animeapp.data.remote

import com.seekho.animeapp.data.remote.dto.AnimeDetailResponse
import com.seekho.animeapp.data.remote.dto.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(
        @Path("id") id: Int
    ): AnimeDetailResponse
}
