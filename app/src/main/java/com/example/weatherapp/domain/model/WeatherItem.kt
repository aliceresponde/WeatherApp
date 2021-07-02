package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherItem(
    val id: Int,
    val name: String,
    val dtPlace: String,
    val description: String,
    val temp: Float,
    val humidity: Float,
    val rain: Float,
    val windSpeed: Float
) :Parcelable