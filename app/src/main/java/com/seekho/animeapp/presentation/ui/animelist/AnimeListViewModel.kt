package com.seekho.animeapp.presentation.ui.animelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.animeapp.domain.model.Anime
import com.seekho.animeapp.domain.repository.AnimeRepository
import com.seekho.animeapp.util.NetworkResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent

    private var currentPage = 1
    private var isLoading = false

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            when (val result = repository.getTopAnime(currentPage)) {

                is NetworkResult.Success -> {
                    _animeList.value = _animeList.value + result.data
                    currentPage++
                }

                is NetworkResult.Error -> {
                    _errorEvent.emit(
                        result.message ?: "Failed to load anime list"
                    )
                }

                is NetworkResult.Loading -> {
                }
            }
            isLoading = false
        }
    }
}


