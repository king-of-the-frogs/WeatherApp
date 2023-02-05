package com.example.weathertoday.features.weather_screen.data

import com.example.weathertoday.features.weather_screen.data.model.MainWeatherRemoteModel
import com.example.weathertoday.features.weather_screen.domain.model.MainModel
import com.example.weathertoday.features.weather_screen.domain.model.MainWeatherModel
import com.example.weathertoday.features.weather_screen.domain.model.SysModel
import com.example.weathertoday.features.weather_screen.domain.model.WindModel


fun MainWeatherRemoteModel.mainToDomain() = MainModel(
    temp = this.main.temp,
    tempMax = this.main.tempMax,
    tempMin = this.main.tempMin,
    pressure = this.main.pressure,
    humidity = this.main.humidity,
    feelsLike = this.main.feelsLike,
)

fun MainWeatherRemoteModel.windToDomain() = WindModel(
    speed = this.wind.speed
)

fun MainWeatherRemoteModel.sysToDomain() = SysModel(
    sunset = this.sys.sunset,
    sunrise = this.sys.sunrise
)

fun MainWeatherRemoteModel.toDomain() = MainWeatherModel(
    main = mainToDomain(),
    wind = windToDomain(),
    sys = sysToDomain(),
    date = date,
)