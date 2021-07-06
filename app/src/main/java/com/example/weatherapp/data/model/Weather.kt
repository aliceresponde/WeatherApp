package com.example.weatherapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(

    val uId: Int,
    val cityId: Int,
    val name: String,
    val wind: Wind,
    val networkWeatherDescription: List<NetworkWeatherDescription>,
    val networkWeatherCondition: NetworkWeatherCondition
) : Parcelable
