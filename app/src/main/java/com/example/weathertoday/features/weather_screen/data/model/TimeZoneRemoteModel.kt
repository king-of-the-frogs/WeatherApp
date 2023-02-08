package com.example.weathertoday.features.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class TimeZoneRemoteModel (

    @SerializedName("time_zone")
    val timeZone: Float,

)