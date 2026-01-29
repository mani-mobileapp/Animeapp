package com.seekho.animeapp

import android.content.Context
import com.seekho.animeapp.data.local.AnimeDatabase
import com.seekho.animeapp.data.remote.RetrofitInstance
import com.seekho.animeapp.data.repository.AnimeRepositoryImpl
import com.seekho.animeapp.domain.repository.AnimeRepository

class AppContainer(context: Context) {

    private val database = AnimeDatabase.getInstance(context)
    private val api = RetrofitInstance.api

    val repository: AnimeRepository =
        AnimeRepositoryImpl(api, database.animeDao(), context)
}
