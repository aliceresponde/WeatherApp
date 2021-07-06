package com.example.weatherapp.data.datasource

import com.example.weatherapp.data.local.ForecastDao
import com.example.weatherapp.data.local.ForecastEntity
import com.example.weatherapp.data.local.Place
import com.example.weatherapp.data.local.PlacesDao
import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.local.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun savePlace(place: Place)
    suspend fun getPlaceById(id: Int): Place
    suspend fun deletePlaceWithLatLong(lat: Double, long: Double)
    suspend fun getPlaceByLatLon(latitude: Double, longitude: Double): Place
    suspend fun deleteAllPlaces()

    fun getAllMarkersFlow(): Flow<List<Place>>
    fun getPlaceByNameFlow(name: String): Flow<List<Place>>

    suspend fun saveCurrentWeather(entity: WeatherEntity)
    suspend fun getCurrentWeather(cityName: String): WeatherEntity

    suspend fun saveForecastWeather(forecast: List<ForecastEntity>, cityName: String)
    suspend fun getForecastWeather(cityName: String): List<ForecastEntity>
}

class RoomDataSource(
    private val placesDao: PlacesDao,
    private val weatherDao: WeatherDao,
    private val forecastDao: ForecastDao,
) :
    LocalDataSource {
    override suspend fun savePlace(place: Place) {
        placesDao.insert(place)
    }

    override suspend fun getPlaceByLatLon(latitude: Double, longitude: Double): Place {
        return placesDao.getPlaceBy(latitude, longitude)
    }

    override suspend fun deleteAllPlaces() {
        placesDao.deleteAll()
    }

    override suspend fun getPlaceById(id: Int): Place {
        return placesDao.getPlaceBy(id)
    }

    override fun getPlaceByNameFlow(name: String): Flow<List<Place>> {
        return placesDao.getPlacesByNameFlow(name)
    }

    override suspend fun deletePlaceWithLatLong(lat: Double, long: Double) {
        placesDao.deletePlaceWithLatLong(lat, long)
    }

    override fun getAllMarkersFlow(): Flow<List<Place>> {
        return placesDao.getAllPlacesFlow()
    }

    // ================== current weather ==================================================
    override suspend fun saveCurrentWeather(entity: WeatherEntity) {
        weatherDao.insertCurrentWeather(entity)
    }

    override suspend fun getCurrentWeather(cityName: String): WeatherEntity {
        return weatherDao.getCurrentWeatherByCity(cityName)
    }

    //======================= forecast ===================
    override suspend fun saveForecastWeather(forecast: List<ForecastEntity>,cityName: String) {
        return forecastDao.insertForecast(forecast, cityName)
    }

    override suspend fun getForecastWeather(cityName: String): List<ForecastEntity> {
        return forecastDao.getCityForeCast(cityName)
    }
}
