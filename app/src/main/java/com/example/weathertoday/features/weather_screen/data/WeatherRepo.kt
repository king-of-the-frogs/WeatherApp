package com.example.weathertoday.features.weather_screen.data

import com.example.weathertoday.features.weather_screen.domain.model.MainWeatherModel

interface WeatherRepo {

    suspend fun getWeather(city: String): MainWeatherModel

}