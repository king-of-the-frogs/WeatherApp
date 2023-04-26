package com.example.weathertoday.features.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class MainRemoteModel(

    @SerializedName("temp")
    val temp: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("humidity")
    val humidity: Long,
    @SerializedName("pressure")
    val pressure: Long,

)
