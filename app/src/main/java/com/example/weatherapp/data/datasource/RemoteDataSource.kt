package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.remote.WeatherApiService
import javax.inject.Inject

interface RemoteDataSource {
//    suspend fun getOneCall(latitude: Long, longitude: Long)
//    suspend fun getTodayWeather(latitude: Long, longitude: Long)
//    suspend fun getLastFiveDaysWeather(latitude: Long, longitude: Long)
}

class RetrofitDataSource @Inject constructor (val  apiService: WeatherApiService): RemoteDataSource {

}