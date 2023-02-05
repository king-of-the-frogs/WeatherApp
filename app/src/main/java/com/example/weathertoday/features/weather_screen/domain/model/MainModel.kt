package com.example.weathertoday.features.weather_screen.domain.model

data class MainModel(
    val temp: Float,
    val tempMax: Float,
    val tempMin: Float,
    val humidity: Long,
    val pressure: Long,
    val feelsLike: Float,
)

