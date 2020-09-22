package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.local.PreferencesHelper

interface ChangeUnitSystemUseCase {
    fun invoke(unitSystem: String)
}

class ChangeUnitSystemUseCaseImp(private val preferencesHelper: PreferencesHelper) :
    ChangeUnitSystemUseCase {
    override fun invoke(unitSystem: String) {
        preferencesHelper.saveSystemUnits(unitSystem)
    }
}
