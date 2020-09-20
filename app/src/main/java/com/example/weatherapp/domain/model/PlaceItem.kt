package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceItem(val lat: Double, val long: Double, val name: String = "") : Parcelable