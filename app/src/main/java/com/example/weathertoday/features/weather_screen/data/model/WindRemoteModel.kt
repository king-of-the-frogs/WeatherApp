package com.example.weathertoday.features.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class  WindRemoteModel(
    @SerializedName("speed")
    val speed: Float,
)