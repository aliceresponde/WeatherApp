package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastWeatherItem
import com.example.weatherapp.domain.toCurrentWeatherItem
import com.example.weatherapp.domain.toForeCastWeatherItemList
import com.example.weatherapp.repository.WeatherRepository

interface GetCurrentWeatherUseCase {
    suspend fun getCurrentWeatherByLocation(locationName: String): RemoCurrentWeatherResponse
    suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): RemoCurrentWeatherResponse

    suspend fun getForecastWeatherLatLng(lat: Double, long: Double): RemoForeCastWeatherResponse
    suspend fun getForecastWeatherByLocationName(locationName: String): RemoForeCastWeatherResponse

}

class GetCurrentWeatherUseCaseImp(val repository: WeatherRepository) : GetCurrentWeatherUseCase {

    override suspend fun getCurrentWeatherByLocation(locationName: String): RemoCurrentWeatherResponse {
        return try {
            val data = repository.getCurrentWeatherByLocation(locationName).toCurrentWeatherItem()
            RemoCurrentWeatherResponse.Success(data = data)
        } catch (e: Throwable) {
            RemoCurrentWeatherResponse.Error
        }
    }

    override suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): RemoCurrentWeatherResponse {
        return try {
            val data =
                repository.getCurrentWeatherByLatLon(latitude, longitude).toCurrentWeatherItem()
            RemoCurrentWeatherResponse.Success(data = data)
        } catch (e: Throwable) {
            RemoCurrentWeatherResponse.Error
        }
    }

    override suspend fun getForecastWeatherLatLng(
        lat: Double,
        long: Double
    ): RemoForeCastWeatherResponse {
        return try {
            val response = repository.getForecastWeatherLatLng(lat, long)
            RemoForeCastWeatherResponse.Success(data = response.toForeCastWeatherItemList())
        } catch (e: Throwable) {
            RemoForeCastWeatherResponse.Error
        }
    }

    override suspend fun getForecastWeatherByLocationName(locationName: String): RemoForeCastWeatherResponse {
        return try {
            val response = repository.getForecastWeatherByLocationName(locationName)
            RemoForeCastWeatherResponse.Success(data = response.toForeCastWeatherItemList())
        } catch (e: Throwable) {
            RemoForeCastWeatherResponse.Error
        }
    }
}

sealed class RemoCurrentWeatherResponse {
    class Success(val data: CurrentWeatherItem) : RemoCurrentWeatherResponse()
    object Error : RemoCurrentWeatherResponse()
}

sealed class RemoForeCastWeatherResponse {
    class Success(val data: List<ForecastWeatherItem>) : RemoForeCastWeatherResponse()
    object Error : RemoForeCastWeatherResponse()
}

