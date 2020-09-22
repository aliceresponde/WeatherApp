package com.example.weatherapp.ui.cities

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.RemoCurrentWeatherResponse
import com.example.weatherapp.domain.usecases.RemoForeCastWeatherResponse
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastWeatherItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getCurrentWeatherUC: GetCurrentWeatherUseCase
) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherItem>()
    val currentWeather: LiveData<CurrentWeatherItem> get() = _currentWeather

    private val _forecastWeatherList = MutableLiveData<List<ForecastWeatherItem>>()
    val forecastWeatherList: LiveData<List<ForecastWeatherItem>> get() = _forecastWeatherList

    private val _showCurrentWeatherCard = MutableLiveData<Boolean>()
    val showCurrentWeatherCard: LiveData<Boolean> get() = _showCurrentWeatherCard

    private val _showForecastRecycler = MutableLiveData<Boolean>()
    val showForecastRecycler: LiveData<Boolean> get() = _showCurrentWeatherCard
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loadingVisibility = MutableLiveData<Int>()
    val loadingVisibility: LiveData<Int> get() = _loadingVisibility

    fun getCurrentWeatherBy(latitude: Double, longitude: Double) {
        viewModelScope.launch(coroutineDispatcher) {
            _showCurrentWeatherCard.postValue(false)
            _loadingVisibility.postValue(VISIBLE)
            val response = getCurrentWeatherUC.getCurrentWeatherByLatLon(latitude, longitude)
            when (response) {
                is RemoCurrentWeatherResponse.Success -> {
                    _currentWeather.postValue(response.data)
                    _showCurrentWeatherCard.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                RemoCurrentWeatherResponse.Error -> {
                    _loadingVisibility.postValue(GONE)
                    _errorMessage.postValue("Verify internet connection or find other location")
                }
            }
        }
    }

    fun getCurrentWeatherByLocationName(locationName: String) {
        if (locationName.isEmpty()) return
        viewModelScope.launch(coroutineDispatcher) {
            _showCurrentWeatherCard.postValue(false)
            _loadingVisibility.postValue(VISIBLE)

            val response = getCurrentWeatherUC.getCurrentWeatherByLocation(locationName)
            when (response) {
                is RemoCurrentWeatherResponse.Success -> {
                    _currentWeather.postValue(response.data)
                    _showCurrentWeatherCard.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                RemoCurrentWeatherResponse.Error -> {
                    _errorMessage.postValue("Verify internet connection or find other location")
                    _loadingVisibility.postValue(GONE)
                }
            }
        }
    }

    fun getForecastWeatherLatLng(lat: Double, long: Double) {
        viewModelScope.launch(coroutineDispatcher) {
            _showForecastRecycler.postValue(false)
            _loadingVisibility.postValue(VISIBLE)

            val response = getCurrentWeatherUC.getForecastWeatherLatLng(lat, long)
            when (response) {
                is RemoForeCastWeatherResponse.Success -> {
                    _forecastWeatherList.postValue(response.data)
                    _showForecastRecycler.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                RemoForeCastWeatherResponse.Error -> {
                    _errorMessage.postValue("Verify internet connection or find other location")
                    _loadingVisibility.postValue(GONE)
                }
            }
        }
    }

    fun getForecastWeatherByLocationName(locationName: String){
        if (locationName.isEmpty()) return
        viewModelScope.launch(coroutineDispatcher) {
            _showForecastRecycler.postValue(false)
            _loadingVisibility.postValue(VISIBLE)
            val response = getCurrentWeatherUC.getForecastWeatherByLocationName(locationName)
            when (response) {
                is RemoForeCastWeatherResponse.Success -> {
                    _forecastWeatherList.postValue(response.data)
                    _showForecastRecycler.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                RemoForeCastWeatherResponse.Error -> {
                    _errorMessage.postValue("Verify internet connection or find other location")
                    _loadingVisibility.postValue(GONE)
                }
            }
        }
    }
}