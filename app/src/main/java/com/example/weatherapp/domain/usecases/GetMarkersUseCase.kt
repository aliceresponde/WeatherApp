package com.example.weatherapp.domain.usecases

import com.example.weatherapp.domain.model.PlaceItem
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface  GetMarkersUseCase {
    operator fun invoke(): Flow<List<PlaceItem>>

}

class GetMarkersUseCaseImp @Inject constructor(private val repository: WeatherRepository): GetMarkersUseCase {
    override fun invoke(): Flow<List<PlaceItem>> {
        return repository.getAllMarkers().map { placeList ->
            placeList.map { it.toPlaceItem() }
        }
    }
}

