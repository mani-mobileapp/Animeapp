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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isPaginating = MutableStateFlow(false)
    val isPaginating: StateFlow<Boolean> = _isPaginating

    private var currentPage = 1
    private var isLastPage = false

    fun loadNextPage() {
        if (_isLoading.value || _isPaginating.value || isLastPage) return

        viewModelScope.launch {
            if (currentPage == 1) _isLoading.value = true
            else _isPaginating.value = true

            val result = repository.getTopAnime(currentPage)
            if (result is NetworkResult.Success) {
                if (result.data.isEmpty()) isLastPage = true
                _animeList.value = _animeList.value + result.data
            }

            _isLoading.value = false
            _isPaginating.value = false
            currentPage++
        }
    }

    fun refreshAnimeList() {
        currentPage = 1
        _animeList.value = emptyList()
        loadNextPage()
    }

}



