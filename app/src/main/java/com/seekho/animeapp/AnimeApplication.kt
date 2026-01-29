package com.seekho.animeapp

import android.app.Application
import androidx.room.Room
import com.seekho.animeapp.data.local.AnimeDatabase
import com.seekho.animeapp.data.remote.AnimeApi
import com.seekho.animeapp.data.repository.AnimeRepositoryImpl
import com.seekho.animeapp.domain.repository.AnimeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}

