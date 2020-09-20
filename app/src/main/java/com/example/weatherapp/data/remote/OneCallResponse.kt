package com.example.weatherapp.data.remote

data class OneCallResponse(
    val lat: Float,
    val lon: Float,
    val daily: DailyDTO
) {
}

data class DailyDTO (
    val dt: Long,

)


