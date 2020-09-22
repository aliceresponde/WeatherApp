package com.example.weatherapp.ui.cities

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.RemoCurrentWeatherResponse
import com.example.weatherapp.domain.model.CurrentWeatherItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getCurrentWeatherUC: GetCurrentWeatherUseCase
) : ViewModel() {

        private val _currentWeather = MutableLiveData<CurrentWeatherItem>()
        val currentWeather: LiveData<CurrentWeatherItem> get() = _currentWeather

        private val _showCurrentWeatherCard = MutableLiveData<Boolean>()
        val showCurrentWeatherCard: LiveData<Boolean> get() = _showCurrentWeatherCard

    fun getCurrentWeatherBy(latitude: Double, longitude:Double){
        viewModelScope.launch (coroutineDispatcher){
            _showCurrentWeatherCard.postValue(false)
            val response =  getCurrentWeatherUC.getCurrentWeatherByLatLon(latitude, longitude)
            when(response){
                is RemoCurrentWeatherResponse.Success -> {
                    _currentWeather.postValue(response.data)
                    _showCurrentWeatherCard.postValue(true)
                }
                RemoCurrentWeatherResponse.Error -> {}
            }
        }
    }

    fun getCurrentWeatherByLocationName(locationName: String) {
        viewModelScope.launch (coroutineDispatcher){
            _showCurrentWeatherCard.postValue(false)
            val response =  getCurrentWeatherUC.getCurrentWeatherByLocation(locationName)
            when(response){
                is RemoCurrentWeatherResponse.Success -> {
                    _currentWeather.postValue(response.data)
                    _showCurrentWeatherCard.postValue(true)
                }
                RemoCurrentWeatherResponse.Error -> {}
            }
        }
    }
}