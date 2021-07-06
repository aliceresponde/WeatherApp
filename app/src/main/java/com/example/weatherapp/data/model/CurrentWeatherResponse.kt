package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Today forecast
 */
data class CurrentWeatherResponse(
    @SerializedName("id")
    val cityId: Long,
    val name: String,
    val wind: Wind,
    @SerializedName("dt")
    val dateUTC: Long,
    @SerializedName("weather")
    val networkWeatherDescriptions: List<NetworkWeatherDescription>,
    @SerializedName("main")
    val networkWeatherCondition: NetworkWeatherCondition,
    @SerializedName("sys")
    val system : SystemResponse,
    @SerializedName("coord")
    val coord: CoordResponse
)
