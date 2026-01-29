package com.seekho.animeapp.domain.model


data class Anime(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val episodes: Int,
    val rating: Double,
    val synopsis: String,
    val trailerUrl: String?
)
