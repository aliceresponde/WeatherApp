package com.example.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_table")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityId:Long,
    val cityName: String,
    val country: String,

    val mainTemp: Double,
    val mainMinTemp: Double,
    val mainMaxTemp: Double,
    val mainHumidity: Double,
    val mainPressure: Double,
    val mainFeel: Double,

    val windSpeed: Double,

    val weatherIcon: String,
    val weatherName: String,
    val weatherDesc: String,

    val date: String,
    val dt: Long,
    val timeStamp: Long
)