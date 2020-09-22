package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.NetworkWeatherForecastResponse
import com.example.weatherapp.data.remote.WeatherApiService
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getCurrentWeatherByLocation(locationName: String): CurrentWeather
    suspend fun getCurrentWeatherByLatLon(latitude: Double, longitude: Double): CurrentWeather
    suspend fun getForecastWeatherLatLng(lat: Double, long: Double): NetworkWeatherForecastResponse
    suspend fun getForecastWeatherByLocationName(locationName: String): NetworkWeatherForecastResponse
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

    override suspend fun getForecastWeatherLatLng(
        lat: Double,
        long: Double
    ): NetworkWeatherForecastResponse {
        return apiService.getWeatherForecast(lat, long)
    }

    override suspend fun getForecastWeatherByLocationName(locationName: String): NetworkWeatherForecastResponse {
        return apiService.getWeatherForecast(locationName)
    }
}