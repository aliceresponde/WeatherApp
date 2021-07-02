package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CityResponse(
    val id: Long,
    val name: String,
    val country: String,
    @SerializedName("coord")
    val coord: CoordResponse
)
