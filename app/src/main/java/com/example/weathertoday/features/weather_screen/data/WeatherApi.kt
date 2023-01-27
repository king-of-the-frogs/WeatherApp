package com.example.weathertoday.features.weather_screen.data

import com.example.weathertoday.features.weather_screen.data.model.MainWeatherRemoteModel
import com.example.weathertoday.features.weather_screen.di.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY
    ): MainWeatherRemoteModel
}