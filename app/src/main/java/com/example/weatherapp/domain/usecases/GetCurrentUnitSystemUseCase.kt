package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.local.PreferencesHelper

interface GetCurrentUnitSystemUseCase {
    operator fun invoke(): String
}

class GetCurrentUnitSystemUseCaseImp(private val preferencesHelper: PreferencesHelper) :
    GetCurrentUnitSystemUseCase {
    override fun invoke(): String = preferencesHelper.getSystemUnits()
}
