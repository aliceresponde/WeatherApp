package com.example.weatherapp.ui.config

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.domain.usecases.ChangeUnitSystemUseCase
import com.example.weatherapp.domain.usecases.GetCurrentUnitSystemUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ConfigViewModel @ViewModelInject constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val changeUnitSystemUC: ChangeUnitSystemUseCase,
    private val getCurrentUnitSystemUC: GetCurrentUnitSystemUseCase
) : ViewModel() {

    private val _isChangeReady = MutableLiveData<Boolean>()
    val isChangeReady: LiveData<Boolean> get() = _isChangeReady


    fun updateSystemPreference(value: String) {
        changeUnitSystemUC.invoke(value)
    }

    fun getCurrentSystem(): String = getCurrentUnitSystemUC()
}