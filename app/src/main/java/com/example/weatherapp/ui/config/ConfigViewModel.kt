package com.example.weatherapp.ui.config

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.usecases.ChangeUnitSystemUseCase
import com.example.weatherapp.domain.usecases.DeleteAllMarkersUseCase
import com.example.weatherapp.domain.usecases.GetCurrentUnitSystemUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val changeUnitSystemUC: ChangeUnitSystemUseCase,
    private val deleteAllMarkersUC: DeleteAllMarkersUseCase,
    private val getCurrentUnitSystemUC: GetCurrentUnitSystemUseCase
) : ViewModel() {

    private val _isChangeReady = MutableLiveData<Boolean>()
    val isChangeReady: LiveData<Boolean> get() = _isChangeReady

    fun deleteAllMarkers() {
        viewModelScope.launch(coroutineDispatcher) {
            deleteAllMarkersUC.invoke()
            _isChangeReady.postValue(true)
        }
    }

    fun updateSystemPreference(value: String) {
        changeUnitSystemUC.invoke(value)
    }

    fun getCurrentSystem(): String = getCurrentUnitSystemUC()
}