package com.example.weatherapp.data.model

import android.os.Parcelable
import com.example.weatherapp.data.model.NetworkWeatherCondition
import com.example.weatherapp.data.model.NetworkWeatherDescription
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(

    val uId: Int,
    val cityId: Int,
    val name: String,
    val wind: Wind,
    val networkWeatherDescription: List<NetworkWeatherDescription>,
    val networkWeatherCondition: NetworkWeatherCondition
) : Parcelable
