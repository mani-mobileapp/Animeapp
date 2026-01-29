package com.seekho.animeapp.data.remote.dto

data class AnimeResponse(
    val pagination: PaginationDto,
    val data: List<AnimeDto>
)

data class PaginationDto(
    val current_page: Int,
    val has_next_page: Boolean
)
