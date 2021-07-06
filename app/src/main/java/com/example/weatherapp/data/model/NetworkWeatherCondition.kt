package com.example.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Main
 */
@Parcelize
data class NetworkWeatherCondition(
    var temp: Double,
    var temp_min: Double,
    var temp_max: Double,
    var feels_like: Double,
    val pressure: Double,
    val humidity: Double
) : Parcelable
