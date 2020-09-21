package com.example.weatherapp.domain

import com.example.weatherapp.data.local.PreferencesHelper

interface ChangeUnitSystemUseCase {
   open fun invoke(unitSystem: String)
}

class ChangeUnitSystemUseCaseImp (private val preferencesHelper: PreferencesHelper) : ChangeUnitSystemUseCase {
    override fun invoke(unitSystem: String) {
        preferencesHelper.saveSystemUnits(unitSystem)
    }
}
