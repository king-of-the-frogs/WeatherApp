package com.example.weathertoday.features.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class MainWeatherRemoteModel(

    @SerializedName("main")
    val main: MainRemoteModel,
    @SerializedName("wind")
    val wind: WindRemoteModel,
    @SerializedName("sys")
    val sys: SysRemoteModel,
    @SerializedName("dt")
    val date: Long,
    @SerializedName("timezone")
    val timezone: TimeZoneRemoteModel

)