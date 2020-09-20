package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.local.Place
import com.example.weatherapp.repository.WeatherRepository
import javax.inject.Inject

interface SaveMarkerUseCase {
    suspend operator fun invoke(latitude: Double, logitud: Double)
}

class SaveMarkerUseCaseImp @Inject constructor(private val repository: WeatherRepository):
    SaveMarkerUseCase {
    override suspend fun invoke(latitude: Double, logitud: Double) {
        repository.savePlace(Place(lat = latitude, lng = logitud, name = "place"))
    }
}
