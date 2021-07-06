package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.model.ForecastItem
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.ui.common.Result

interface GetForecastWeatherUseCase {
    suspend operator fun invoke(locationName: String): Result<List<ForecastItem>>
}

class GetForecastWeatherUseCaseImp(val repository: WeatherRepository) : GetForecastWeatherUseCase {
    override suspend fun invoke(locationName: String): Result<List<ForecastItem>> {
        return repository.fetchForecastWeatherByLocationName(locationName)
    }
}


