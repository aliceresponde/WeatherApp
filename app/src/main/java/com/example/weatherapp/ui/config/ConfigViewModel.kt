package com.example.weatherapp.ui.config

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.ChangeUnitSystemUseCase
import com.example.weatherapp.domain.DeleteAllMarkersUseCase
import com.example.weatherapp.domain.GetCurrentUnitSystemUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val changeUnitSystemUC: ChangeUnitSystemUseCase,
    private val deleteAllMarkersUC: DeleteAllMarkersUseCase,
    private val getCurrentUnitSystemUC: GetCurrentUnitSystemUseCase
) : ViewModel() {

    fun deleteAllMarkers() {
        viewModelScope.launch(coroutineDispatcher) {
            deleteAllMarkersUC.invoke()
        }
    }

    fun updateSystemPreference(value: String) {
        changeUnitSystemUC.invoke(value)
    }

    fun getCurrentSystem(): String = getCurrentUnitSystemUC()
}