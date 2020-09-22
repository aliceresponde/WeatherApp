package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.NetworkWeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Holds API Calls from Weather App
 */
interface WeatherApiService {

    // ==================== Current Weather =====================
    //https://api.openweathermap.org/data/2.5/
    @GET("weather")
    suspend fun getCurrentWeatherByLocationName(
        @Query("q") locationName: String
    ): CurrentWeather

    @GET("weather")
    suspend fun getCurrentWeatherByLatLon(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): CurrentWeather

    // ==================== FORECAST =====================

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") locationName: String,
    ): NetworkWeatherForecastResponse


    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): NetworkWeatherForecastResponse
}