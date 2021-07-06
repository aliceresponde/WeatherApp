package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(
    @SerializedName("list")
    val weathers: List<ForecastResponse>,
    val city: CityResponse
)
