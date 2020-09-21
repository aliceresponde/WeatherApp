package com.example.weatherapp.domain.usecases

import com.example.weatherapp.data.local.Place
import com.example.weatherapp.domain.model.PlaceItem

 fun Place.toPlaceItem() = PlaceItem(lat = lat, long = lng, name = name)
