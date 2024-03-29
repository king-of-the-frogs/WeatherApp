package com.example.weathertoday.features.weather_screen.ui

import com.example.weathertoday.base.Event
import com.example.weathertoday.features.weather_screen.domain.model.MainWeatherModel

data class ViewState(

    val temp: Float,
    val tempMax: Float,
    val tempMin: Float,
    val feelsLike: Float,
    val pressure: Long,
    val humidity: Long,
    val sunrise: Long,
    val sunset: Long,
    val date: Long,
    val speed: Float,
    val isLoading: Boolean,
    var city: String,
    val timeZone: Int,

    )

// События, которые могут произойти в процессе загрузки данных
sealed class DataEvent: Event {
    object WeatherLoaded: DataEvent() // событие загрузки погоды
    data class OnWeatherSucceed(val data: MainWeatherModel) : DataEvent() // успешное получение погодных данных
}

// События, которые могут быть инициированы пользователем
sealed class UiEvent: Event {
    data class LoadWeatherFromCity(val city: String): UiEvent() // загрузка погоды для указанного города
}