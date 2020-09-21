package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.local.Place
import com.example.weatherapp.data.local.PlacesDao
import com.example.weatherapp.domain.model.PlaceItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun savePlace(place: Place)
    suspend fun getPlaceById(id: Int): Place
    suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>>
    suspend fun deletePlaceWithLatLong(lat: Double, long: Double)
    fun getAllMarkersFlow(): Flow<List<Place>>
    suspend fun getPlaceByLatLon(latitude: Double, longitude: Double): Place
}

class RoomDataSource (private val placesDao: PlacesDao) : LocalDataSource {
    override suspend fun savePlace(place: Place) {
        placesDao.insert(place)
    }

    override suspend fun getPlaceByLatLon(latitude: Double, longitude: Double): Place {
        return placesDao.getPlaceBy(latitude, longitude)
    }

    override suspend fun getPlaceById(id: Int): Place {
        return placesDao.getPlaceBy(id)
    }

    override suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>> {
        return placesDao.getPlacesByNameFlow(name)
    }

    override suspend fun deletePlaceWithLatLong(lat: Double, long: Double) {
        placesDao.deletePlaceWithLatLong(lat, long)
    }

    override fun getAllMarkersFlow(): Flow<List<Place>> {
        return placesDao.getAllPlacesFlow()
    }
}
