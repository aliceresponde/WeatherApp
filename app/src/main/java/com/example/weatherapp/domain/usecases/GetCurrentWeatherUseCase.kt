package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.common.Result

interface GetCurrentWeatherUseCase {
    suspend operator fun invoke(locationName: String): Result<CurrentWeatherItem>
}

class GetCurrentWeatherUseCaseImp(val repository: WeatherRepository) : GetCurrentWeatherUseCase {
    override suspend fun invoke(locationName: String): Result<CurrentWeatherItem> {
        return try {
            repository.fetchCurrentWeatherByLocation(locationName)
        } catch (e: Throwable) {
            Result.Error(Exception())
        }
    }
}

