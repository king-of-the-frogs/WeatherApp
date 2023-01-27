package com.example.weathertoday.features.weather_screen.data

import com.example.weathertoday.features.weather_screen.domain.model.MainWeatherModel

class WeatherRepoImpl(private val weatherRemoteSource: WeatherRemoteSource) : WeatherRepo {

    override suspend fun getWeather(city: String): MainWeatherModel {
        return weatherRemoteSource.getWeather(city = city).toDomain()
    }

}