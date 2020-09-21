package com.example.weatherapp.domain

import com.example.weatherapp.repository.WeatherRepository

interface DeleteAllMarkersUseCase {
    suspend operator fun invoke()
}

class DeleteAllMarkersUseCaseImp(private val repository: WeatherRepository): DeleteAllMarkersUseCase {
    override suspend fun invoke() {
        repository.deleteAllPlaces()
    }
}
