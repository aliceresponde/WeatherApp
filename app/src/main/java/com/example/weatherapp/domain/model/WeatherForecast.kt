package com.example.weatherapp.domain.model

import com.example.weatherapp.data.model.NetworkWeatherCondition
import com.example.weatherapp.data.model.NetworkWeatherDescription
import com.example.weatherapp.data.model.Wind

data class WeatherForecast(
    val uID: Int,

    val date: String,

    val wind: Wind,

    val networkWeatherDescription: List<NetworkWeatherDescription>,

    val networkWeatherCondition: NetworkWeatherCondition
)
