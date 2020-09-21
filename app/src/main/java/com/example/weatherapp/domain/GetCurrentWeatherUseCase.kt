package com.example.weatherapp.domain

import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.repository.WeatherRepository

interface GetCurrentWeatherUseCase {
    suspend fun getCurrentWeatherByLocation(locationName: String) : RemoCurrentWeatherResponse
    suspend fun getCurrentWeatherByLatLon(latitude: Double, longitude: Double) : RemoCurrentWeatherResponse
}

class GetCurrentWeatherUseCaseImp(val repository: WeatherRepository) : GetCurrentWeatherUseCase {
    override suspend fun getCurrentWeatherByLocation(locationName: String): RemoCurrentWeatherResponse{
        return try {
            val data =  repository.getCurrentWeatherByLocation(locationName).toCurrentWeatherItem()
            RemoCurrentWeatherResponse.Success(data = data)
        }catch (e : Throwable){
            RemoCurrentWeatherResponse.Error
        }
    }

    override suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): RemoCurrentWeatherResponse {
        return try {
            val data =  repository.getCurrentWeatherByLatLon(latitude, longitude).toCurrentWeatherItem()
            RemoCurrentWeatherResponse.Success(data = data)
        }catch (e : Throwable){
            RemoCurrentWeatherResponse.Error
        }
    }
}

sealed class RemoCurrentWeatherResponse {
    class Success(val data: CurrentWeatherItem) : RemoCurrentWeatherResponse()
    object Error : RemoCurrentWeatherResponse()
}

