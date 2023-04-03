package com.example.weathertoday.features.weather_screen.di

import com.example.weathertoday.features.weather_screen.data.WeatherApi
import com.example.weathertoday.features.weather_screen.data.WeatherRemoteSource
import com.example.weathertoday.features.weather_screen.data.WeatherRepo
import com.example.weathertoday.features.weather_screen.data.WeatherRepoImpl
import com.example.weathertoday.features.weather_screen.domain.WeatherIteractor
import com.example.weathertoday.features.weather_screen.ui.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Определяем константу API_KEY для использования в запросах к API сервису погоды
const val API_KEY = "1f82352874e7d7921d10f7b46f4f2aeb"

// Определяем константу BASE_URL для использования в Retrofit
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

// Создаем модуль Koin для экрана погоды
val weatherScreenModule = module {

    // Определяем зависимость для OkHttpClient
    single<OkHttpClient> { OkHttpClient
        .Builder()
        .build()
    }

    // Определяем зависимость для Retrofit, используя BASE_URL и GsonConverterFactory
    single<Retrofit> {  Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get<OkHttpClient>())
        .build()
    }

    // Определяем зависимость для WeatherApi, используя Retrofit
    single<WeatherApi > { get<Retrofit>().create(WeatherApi::class.java) }

    // Определяем зависимость для WeatherRemoteSource, используя WeatherApi
    single { WeatherRemoteSource(get<WeatherApi>()) }

    // Определяем зависимость для WeatherRepo, используя WeatherRemoteSource
    single<WeatherRepo> { WeatherRepoImpl(get<WeatherRemoteSource>()) }

    // Определяем зависимость для WeatherIteractor, используя WeatherRepo
    single { WeatherIteractor(get<WeatherRepo>()) }

    // Определяем зависимость для MainViewModel, используя WeatherIteractor
    viewModel { MainViewModel(get<WeatherIteractor>()) }
}