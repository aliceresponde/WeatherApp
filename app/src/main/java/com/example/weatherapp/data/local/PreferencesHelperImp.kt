package com.example.weatherapp.data.local

import android.content.SharedPreferences
import javax.inject.Inject

const val KEY_UNITS = "units"
const val METRIC = "metric"

class PreferencesHelperImp @Inject constructor(private val preference: SharedPreferences) :
    PreferencesHelper {
    override fun getSystemUnits(): String {
        return preference.getString(KEY_UNITS, METRIC) ?: METRIC
    }

    override fun saveSystemUnits(systemUnit: String) {
        preference.edit().putString(KEY_UNITS, systemUnit).apply()
    }
}