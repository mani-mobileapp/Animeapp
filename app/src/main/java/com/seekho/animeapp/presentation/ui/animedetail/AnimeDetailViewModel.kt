package com.seekho.animeapp.presentation.ui.animedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.animeapp.domain.model.Anime
import com.seekho.animeapp.domain.repository.AnimeRepository
import com.seekho.animeapp.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _anime =
        MutableStateFlow<NetworkResult<Anime>>(NetworkResult.Loading)

    val anime: StateFlow<NetworkResult<Anime>> = _anime

    fun loadAnimeDetail(animeId: Int) {
        viewModelScope.launch {
            _anime.value = NetworkResult.Loading
            _anime.value = repository.getAnimeDetail(animeId)
        }
    }
}




