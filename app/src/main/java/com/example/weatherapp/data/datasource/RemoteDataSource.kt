package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.remote.WeatherApiService
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getCurrentWeatherByLocation(locationName: String): CurrentWeather
    suspend fun getCurrentWeatherByLatLon(latitude: Double, longitude: Double): CurrentWeather

//    suspend fun getOneCall(latitude: Long, longitude: Long)
//    suspend fun getTodayWeather(latitude: Long, longitude: Long)
//    suspend fun getLastFiveDaysWeather(latitude: Long, longitude: Long)
}

class RetrofitDataSource @Inject constructor(private val apiService: WeatherApiService) :
    RemoteDataSource {
    override suspend fun getCurrentWeatherByLocation(locationName: String): CurrentWeather {
        return apiService.getCurrentWeatherByLocationName(locationName)
    }

    override suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): CurrentWeather {
        return apiService.getCurrentWeatherByLatLon(latitude, longitude)
    }
}