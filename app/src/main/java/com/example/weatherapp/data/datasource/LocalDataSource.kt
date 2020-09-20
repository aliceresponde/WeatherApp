package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.local.Place
import com.example.weatherapp.data.local.PlacesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {
    suspend fun savePlace(place: Place)
    suspend fun getPlaceByLatLon(latitude: Long, longitude: Long): Place
    suspend fun getPlaceById(id: Int): Place
    suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>>
}

class RoomDataSource (private val placesDao: PlacesDao) : LocalDataSource {
    override suspend fun savePlace(place: Place) {
        placesDao.insert(place)
    }

    override suspend fun getPlaceByLatLon(latitude: Long, longitude: Long): Place {
        return placesDao.getPlaceBy(latitude, longitude)
    }

    override suspend fun getPlaceById(id: Int): Place {
        return placesDao.getPlaceBy(id)
    }

    override suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>> {
        return placesDao.getPlacesByNameFlow(name)
    }
}
