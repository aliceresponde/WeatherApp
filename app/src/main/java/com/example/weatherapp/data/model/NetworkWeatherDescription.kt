package com.example.weatherapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkWeatherDescription(
    val id: Long,
    @SerializedName("main")
    val weatherName: String,
    @SerializedName("description")
    val weatherDescription: String,
    @SerializedName("icon")
    val weatherIcon: String
) : Parcelable
