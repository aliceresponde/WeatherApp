package com.example.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Main
 */
@Parcelize
data class NetworkWeatherCondition(
    var temp: Double,
    val pressure: Double,
    val humidity: Double
) : Parcelable
