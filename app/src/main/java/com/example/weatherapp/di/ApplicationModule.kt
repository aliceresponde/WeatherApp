package com.example.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.datasource.RetrofitDataSource
import com.example.weatherapp.data.datasource.RoomDataSource
import com.example.weatherapp.data.local.PlacesDao
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.local.WeatherDataBase
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.usecases.DeleteMarkerUseCase
import com.example.weatherapp.domain.usecases.DeleteMarkerUseCaseImp
import com.example.weatherapp.domain.usecases.GetMarkersUseCase
import com.example.weatherapp.domain.usecases.GetMarkersUseCaseImp
import com.example.weatherapp.domain.usecases.SaveMarkerUseCase
import com.example.weatherapp.domain.usecases.SaveMarkerUseCaseImp
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

const val SHARED_PREF_NAME = "weather_preferences"

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    // ============LOCAL
    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideWeatherDataBase(@ApplicationContext context: Context): WeatherDataBase =
        Room.databaseBuilder(context, WeatherDataBase::class.java, "database-name").build()


    @Provides
    @Singleton
    fun providesPlacesDao(dataBase: WeatherDataBase): PlacesDao = dataBase.placeDao()

    // =============Repository
    @Provides
    @Singleton
    fun provideWeatherRepository(
        local: LocalDataSource,
        remote: RemoteDataSource,
        preferencesHelper: PreferencesHelper
    ): WeatherRepository = WeatherRepositoryImp(local, remote, preferencesHelper)

    // ====================DataSource

    @Provides
    fun provideRoomDataSource(placesDao: PlacesDao): LocalDataSource = RoomDataSource(placesDao)

    @Provides
    fun providesRetrofitDataSource(api: WeatherApiService): RemoteDataSource =
        RetrofitDataSource(api)

    // ============Domain
    @Provides
    fun provideSaveMarkerUseCaseImp(repository: WeatherRepository): SaveMarkerUseCase =
        SaveMarkerUseCaseImp(repository)

    @Provides
    fun providesDeleteMarkerUseCase(repository: WeatherRepository): DeleteMarkerUseCase =
        DeleteMarkerUseCaseImp(repository)

    @Provides
    fun providesGetMarkersUseCase(repository: WeatherRepository): GetMarkersUseCase =
        GetMarkersUseCaseImp(repository)
}