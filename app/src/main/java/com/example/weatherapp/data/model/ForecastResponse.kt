package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val dt : Long,
    @SerializedName("dt_txt")
    val date: String,
    val wind: Wind,
    @SerializedName("weather")
    val weather: List<NetworkWeatherDescription>,
    @SerializedName("main")
    val main: NetworkWeatherCondition
)
