package com.example.weatherapp.di

import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.datasource.RetrofitDataSource
import com.example.weatherapp.data.datasource.RoomDataSource
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.local.PreferencesHelperImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {
    // ==================== LOCAL
    @Binds
    @Singleton
    abstract fun bindPreferencesHelper(preferencesHelperImp: PreferencesHelperImp): PreferencesHelper

//     ====================== Repository
//    @Binds
//    @Singleton
//    abstract fun bindRemoteDataSource(retrofitDataSource: RetrofitDataSource): RemoteDataSource
//
//
//    @Binds
//    @Singleton
//    abstract fun bindLocalDataSource(roomDataSource: RoomDataSource): LocalDataSource
}