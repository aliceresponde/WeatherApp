package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.model.CurrentWeatherResponse
import com.example.weatherapp.data.model.ForecastWeatherResponse
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.ui.common.Result
import java.lang.Exception
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getCurrentWeatherByLocation(locationName: String): Result<CurrentWeatherResponse>
    suspend fun getForecastWeatherByLocationName(locationName: String): Result<ForecastWeatherResponse>

    suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): CurrentWeatherResponse

    suspend fun getForecastWeatherLatLng(lat: Double, long: Double): ForecastWeatherResponse
}

class RetrofitDataSource @Inject constructor(private val apiService: WeatherApiService) :
    RemoteDataSource {
    override suspend fun getCurrentWeatherByLocation(locationName: String): Result<CurrentWeatherResponse> {
        return try {
            val remote = apiService.getCurrentWeatherByLocationName(locationName)
            if (remote.isSuccessful) {
                Result.Success(remote.body())
            } else Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): CurrentWeatherResponse {
        return apiService.getCurrentWeatherByLatLon(latitude, longitude)
    }

    override suspend fun getForecastWeatherLatLng(
        lat: Double,
        long: Double
    ): ForecastWeatherResponse {
        return apiService.getWeatherForecast(lat, long)
    }

    override suspend fun getForecastWeatherByLocationName(locationName: String): Result<ForecastWeatherResponse> {
        return try {
            val remote = apiService.getWeatherForecast(locationName)
            if (remote.isSuccessful) Result.Success(remote.body())
            else Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}