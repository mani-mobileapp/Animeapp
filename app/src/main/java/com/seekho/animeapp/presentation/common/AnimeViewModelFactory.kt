package com.seekho.animeapp.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seekho.animeapp.domain.repository.AnimeRepository
import com.seekho.animeapp.presentation.ui.animedetail.AnimeDetailViewModel
import com.seekho.animeapp.presentation.ui.animelist.AnimeListViewModel

class AnimeViewModelFactory(
    private val repository: AnimeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AnimeListViewModel::class.java) ->
                AnimeListViewModel(repository) as T

            modelClass.isAssignableFrom(AnimeDetailViewModel::class.java) ->
                AnimeDetailViewModel(repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}


