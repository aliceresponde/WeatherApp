package com.example.weatherapp.data.remote

import com.example.weatherapp.BuildConfig.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Holds API Calls from Weather App
 */
interface WeatherApiService {

    //https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,minutely&appid=c6e381d8c7ff98f0fee43775817cf6ad
    @GET("data/2.5/onecall")
    suspend fun getWeatherOneCall(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("appid") apiKey: String = API_KEY
    ): OneCallResponse

}