package com.example.weatherapp.repository

import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.local.Place
import com.example.weatherapp.data.local.PreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeatherRepository {
    suspend fun savePlace(place: Place)
    suspend fun getPlaceBy(latitude: Long, longitude: Long): Place
    suspend fun getPlaceBy(id: Int): Place
    suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>>
}

class WeatherRepositoryImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val preferencesHelper: PreferencesHelper
) : WeatherRepository {
    override suspend fun savePlace(place: Place) {
        local.savePlace(place)
    }

    override suspend fun getPlaceBy(latitude: Long, longitude: Long): Place {
        return local.getPlaceByLatLon(latitude, longitude)
    }

    override suspend fun getPlaceBy(id: Int): Place {
        return local.getPlaceById(id)
    }

    override suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>> {
        return local.getPlaceByNameFlow(name)
    }


}
