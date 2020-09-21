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

    /**
    * This function gets the [CurrentWeather] for the [locationName] the
    * user searched for.
    */
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherByLocationName(
        @Query("q") locationName: String,
    ): CurrentWeather

    // This function gets the weather information for the user's location.
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherByLatLon(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
    ): CurrentWeather

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("id") cityId: Int,
    ): Response<NetworkWeatherForecastResponse>

    //https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,minutely
    @GET("data/2.5/onecall")
    suspend fun getWeatherOneCall(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("exclude") exclude: String = "hourly,minutely",
    )
}