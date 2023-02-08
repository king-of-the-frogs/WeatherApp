package com.example.weathertoday.features.weather_screen.data

import com.example.weathertoday.features.weather_screen.data.model.MainWeatherRemoteModel
import com.example.weathertoday.features.weather_screen.data.model.TimeZoneRemoteModel
import com.example.weathertoday.features.weather_screen.domain.model.*


fun MainWeatherRemoteModel.mainToDomain() = MainModel(
    temp = this.main.temp,
    tempMax = this.main.tempMax,
    tempMin = this.main.tempMin,
    feelsLike = this.main.feelsLike,
    pressure = this.main.pressure,
    humidity = this.main.humidity,
)

fun MainWeatherRemoteModel.windToDomain() = WindModel(
    speed = this.wind.speed,
)

fun MainWeatherRemoteModel.sysToDomain() = SysModel(
    sunset = this.sys.sunset,
    sunrise = this.sys.sunrise,
)

fun TimeZoneRemoteModel.timeZoneToDomain() = TimeZoneModel(
    timeZone = this.timeZone.timeZone
)

fun MainWeatherRemoteModel.toDomain() = MainWeatherModel(
    main = mainToDomain(),
    wind = windToDomain(),
    sys = sysToDomain(),
    timeZone = timeZoneToDomain(),
    date = date,
)
