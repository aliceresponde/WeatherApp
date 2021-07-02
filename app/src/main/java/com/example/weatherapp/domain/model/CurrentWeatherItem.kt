package com.example.weatherapp.domain.model

class CurrentWeatherItem (
    val cityId: Long,
    val placeName: String,
    val currentTemp: String,
    val minTemp: String,
    val maxTemp: String,
    val feel: String,
    val date: String,
    val humidity: String,
    val pressure: String,
    val windSpeed:  String,
    val iconUrl: String,
    val weatherName: String,
    val weatherDesc: String,
    val country: String,
    val dt: Long
)
