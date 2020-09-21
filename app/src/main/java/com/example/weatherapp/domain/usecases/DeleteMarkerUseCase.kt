package com.example.weatherapp.domain.usecases

import com.example.weatherapp.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

interface DeleteMarkerUseCase {
    suspend operator fun invoke(latLng: LatLng)
}

class DeleteMarkerUseCaseImp @Inject constructor(private val repository: WeatherRepository) :
    DeleteMarkerUseCase {
    override suspend fun invoke(latLng: LatLng) {
        repository.deletePlaceWithLatLong(latLng.latitude, latLng.longitude)
    }
}