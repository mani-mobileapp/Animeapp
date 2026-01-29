package com.seekho.animeapp.data.mapper

import com.seekho.animeapp.data.remote.dto.AnimeDto
import com.seekho.animeapp.domain.model.Anime


fun AnimeDto.toDomain(): Anime {
    return Anime(
        id = mal_id,
        title = title,
        posterUrl = images.jpg.image_url,
        episodes = episodes ?: 0,
        rating = score ?: 0.0,
        synopsis = synopsis ?: "",
        trailerUrl = trailer?.embed_url
    )
}
