package com.example.weatherapp.data.local

interface PreferencesHelper {
    fun getSystemUnits(): String
    fun saveSystemUnits(systemUnit: String)
}