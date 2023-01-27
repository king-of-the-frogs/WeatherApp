package com.example.weathertoday.features.weather_screen.domain

import com.example.weathertoday.base.attempt
import com.example.weathertoday.features.weather_screen.data.WeatherRepo

class WeatherIteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather(city: String) =
        attempt { weatherRepo.getWeather(city = city) }
}
