package com.example.weatherapp.ui.cities

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CurrentWeatherItem
import com.example.weatherapp.domain.model.ForecastItem
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.GetForecastWeatherUseCase
import com.example.weatherapp.ui.common.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesViewModel @ViewModelInject constructor(
    private val getCurrentWeatherUC: GetCurrentWeatherUseCase,
    private val getForecastWeatherUC: GetForecastWeatherUseCase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherItem>()
    val currentWeather: LiveData<CurrentWeatherItem> get() = _currentWeather

    private val _forecastWeatherList = MutableLiveData<List<ForecastItem>>()
    val forecastList: LiveData<List<ForecastItem>> get() = _forecastWeatherList

    private val _showCurrentWeatherCard = MutableLiveData<Boolean>()
    val showCurrentWeatherCard: LiveData<Boolean> get() = _showCurrentWeatherCard

    private val _showForecastRecycler = MutableLiveData<Boolean>()
    val showForecastRecycler: LiveData<Boolean> get() = _showCurrentWeatherCard

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loadingVisibility = MutableLiveData<Int>()
    val loadingVisibility: LiveData<Int> get() = _loadingVisibility

    private val _currentState = MutableLiveData<String>()
    val currentState: LiveData<String> get() = _currentState

    private val _currentCapital = MutableLiveData<String>()
    val currentCapital: LiveData<String> get() = _currentCapital

    fun getCurrentWeatherByLocationName(locationName: String) {
        if (locationName.isEmpty()) return
        viewModelScope.launch(coroutineDispatcher) {
            _showCurrentWeatherCard.postValue(false)
            _loadingVisibility.postValue(VISIBLE)

            when (val response = getCurrentWeatherUC(locationName)) {
                is Success -> {
                    _currentWeather.postValue(response.data)
                    _showCurrentWeatherCard.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                else -> {
                    _errorMessage.postValue("Verify internet connection or find other location")
                    _loadingVisibility.postValue(GONE)
                }
            }
        }
    }

    fun getForecastWeatherByLocationName(locationName: String) {
        if (locationName.isEmpty()) return
        viewModelScope.launch(coroutineDispatcher) {
            _showForecastRecycler.postValue(false)
            _loadingVisibility.postValue(VISIBLE)

            when (val response = getForecastWeatherUC(locationName)) {
                is Success -> {
                    _forecastWeatherList.postValue(response.data)
                    _showForecastRecycler.postValue(true)
                    _loadingVisibility.postValue(GONE)
                }
                else -> {
                    _errorMessage.postValue("Verify internet connection or find other location")
                    _loadingVisibility.postValue(GONE)
                }
            }
        }
    }

    fun setCurrentState(state: String) {
        _currentState.value = state
    }

    fun setCurrentCapital(capital: String) {
        _currentCapital.value = capital
    }
}