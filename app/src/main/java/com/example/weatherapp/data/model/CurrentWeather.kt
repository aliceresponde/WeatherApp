package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Today forecast
 */
data class CurrentWeather(
    @SerializedName("id")
    val cityId: Int,
    val name: String,
    val wind: Wind,
    @SerializedName("dt")
    val dateUTC: Long,
    @SerializedName("weather")
    val networkWeatherDescriptions: List<NetworkWeatherDescription>,
    @SerializedName("main")
    val networkWeatherCondition: NetworkWeatherCondition
)
