package com.example.weatherapp.repository

import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.model.CurrentWeatherResponse
import com.example.weatherapp.data.model.ForecastWeatherResponse
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastItem
import com.example.weatherapp.domain.toCurrentWeatherEntity
import com.example.weatherapp.domain.toCurrentWeatherItem
import com.example.weatherapp.domain.toForeCastWeatherItemList
import com.example.weatherapp.domain.toForecastEntityList
import com.example.weatherapp.domain.toForecastItemList
import com.example.weatherapp.ui.common.Result
import com.example.weatherapp.ui.common.Result.Error
import com.example.weatherapp.ui.common.Result.Success
import javax.inject.Inject

interface WeatherRepository {
    suspend fun fetchCurrentWeatherByLocation(locationName: String): Result<CurrentWeatherItem>
    suspend fun fetchForecastWeatherByLocationName(locationName: String): Result<List<ForecastItem>>
}

class WeatherRepositoryImp @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val preferencesHelper: PreferencesHelper
) : WeatherRepository {

    override suspend fun fetchCurrentWeatherByLocation(locationName: String): Result<CurrentWeatherItem> {
        return when (val respose: Result<CurrentWeatherResponse> = remote.getCurrentWeatherByLocation(locationName)) {
            is Success -> {
                respose.data?.let { local.saveCurrentWeather(it.toCurrentWeatherEntity()) }
                Success(local.getCurrentWeather(locationName).toCurrentWeatherItem())
            }
            is Error -> Success(local.getCurrentWeather(locationName).toCurrentWeatherItem())
        }
    }

    override suspend fun fetchForecastWeatherByLocationName(locationName: String): Result<List<ForecastItem>> {
        return when (val response = remote.getForecastWeatherByLocationName(locationName)) {
            is Success -> {
                response.data?.let {
                    local.saveForecastWeather(it.toForecastEntityList(), locationName)
                }
                Success(local.getForecastWeather(locationName).toForecastItemList())
            }
            is Error -> Success(local.getForecastWeather(locationName).toForecastItemList())
        }
    }
}
