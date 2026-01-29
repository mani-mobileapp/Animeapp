package com.seekho.animeapp.data.mapper

import com.seekho.animeapp.data.local.entity.AnimeEntity
import com.seekho.animeapp.domain.model.Anime


fun Anime.toEntity(): AnimeEntity {
    return AnimeEntity(
        id = id,
        title = title,
        posterUrl = posterUrl,
        episodes = episodes,
        rating = rating,
        synopsis = synopsis
    )
}

fun AnimeEntity.toDomain(): Anime {
    return Anime(
        id = id,
        title = title,
        posterUrl = posterUrl,
        episodes = episodes,
        rating = rating,
        synopsis = synopsis,
        trailerUrl = null // local DB doesn’t store trailer
    )
}

