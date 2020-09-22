package com.example.weatherapp.repository

import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.local.Place
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.model.CurrentWeather
import com.example.weatherapp.data.model.NetworkWeatherForecastResponse
import com.example.weatherapp.domain.model.PlaceItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeatherRepository {
    suspend fun savePlace(place: Place)
    suspend fun getPlaceBy(latitude: Double, longitude: Double): Place
    suspend fun getPlaceBy(id: Int): Place
    suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>>

    suspend fun deleteAllPlaces()
    suspend fun deletePlaceWithLatLong(lat: Double, long: Double)
    fun getAllMarkers(): Flow<List<Place>>

    //  ================= remote
    suspend fun getCurrentWeatherByLocation(locationName: String): CurrentWeather
    suspend fun getCurrentWeatherByLatLon(latitude: Double, longitude: Double): CurrentWeather
    suspend fun getForecastWeatherLatLng(lat: Double, long: Double): NetworkWeatherForecastResponse
    suspend fun getForecastWeatherByLocationName(locationName: String): NetworkWeatherForecastResponse
}

class WeatherRepositoryImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val preferencesHelper: PreferencesHelper
) : WeatherRepository {
    override suspend fun savePlace(place: Place) {
        local.savePlace(place)
    }

    override suspend fun getPlaceBy(latitude: Double, longitude: Double): Place {
        return local.getPlaceByLatLon(latitude, longitude)
    }

    override suspend fun getPlaceBy(id: Int): Place {
        return local.getPlaceById(id)
    }

    override suspend fun getPlaceByNameFlow(name: String): Flow<List<Place>> {
        return local.getPlaceByNameFlow(name)
    }

    override suspend fun deleteAllPlaces() {
        local.deleteAllPlaces()
    }

    override suspend fun deletePlaceWithLatLong(lat: Double, long: Double) {
        local.deletePlaceWithLatLong(lat, long)
    }

    override fun getAllMarkers(): Flow<List<Place>> {
        return local.getAllMarkersFlow()
    }

    override suspend fun getCurrentWeatherByLocation(locationName: String): CurrentWeather {
        return remote.getCurrentWeatherByLocation(locationName)
    }

    override suspend fun getCurrentWeatherByLatLon(
        latitude: Double,
        longitude: Double
    ): CurrentWeather {
        return remote.getCurrentWeatherByLatLon(latitude, longitude)
    }

    override suspend fun getForecastWeatherLatLng(
        lat: Double,
        long: Double
    ): NetworkWeatherForecastResponse {
        return remote.getForecastWeatherLatLng(lat, long)
    }

    override suspend fun getForecastWeatherByLocationName(locationName: String): NetworkWeatherForecastResponse {
        return remote.getForecastWeatherByLocationName(locationName)
    }
}
