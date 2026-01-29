package com.seekho.animeapp.data.remote.dto

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String?,
    val images: ImagesDto,
    val trailer: TrailerDto?
)

data class ImagesDto(
    val jpg: ImageJpgDto
)

data class ImageJpgDto(
    val image_url: String
)

data class TrailerDto(
    val embed_url: String?
)
