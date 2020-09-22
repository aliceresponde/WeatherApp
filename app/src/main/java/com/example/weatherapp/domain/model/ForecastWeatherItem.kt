package com.example.weatherapp.domain.model

data class ForecastWeatherItem(
    val placeName: String,
    val temp: String,
    val date: String,
    val humidity: String,
    val pressure: String,
    val windSpeed:  String,
    val iconUrl: String,
    val weatherName: String,
    val weatherDesc: String
)