package com.example.weatherapp.domain.model

data class ForecastItem(
    val cityId: Long,
    val cityName: String,
    val country: String,
    val currentTemp: String,
    val minTemp: String,
    val maxTemp: String,
    val feel: String,
    val humidity: String,
    val pressure: String,
    val windSpeed:  String,
    val weatherName: String,
    val weatherDesc: String,
    val iconUrl: String,
    val date: String,
    val dt: Long
)