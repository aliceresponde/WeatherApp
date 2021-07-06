package com.example.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.datasource.LocalDataSource
import com.example.weatherapp.data.datasource.RemoteDataSource
import com.example.weatherapp.data.datasource.RetrofitDataSource
import com.example.weatherapp.data.datasource.RoomDataSource
import com.example.weatherapp.data.local.ForecastDao
import com.example.weatherapp.data.local.PlacesDao
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.data.local.WeatherDataBase
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.usecases.ChangeUnitSystemUseCase
import com.example.weatherapp.domain.usecases.ChangeUnitSystemUseCaseImp
import com.example.weatherapp.domain.usecases.GetCurrentUnitSystemUseCase
import com.example.weatherapp.domain.usecases.GetCurrentUnitSystemUseCaseImp
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.usecases.GetCurrentWeatherUseCaseImp
import com.example.weatherapp.domain.usecases.GetForecastWeatherUseCase
import com.example.weatherapp.domain.usecases.GetForecastWeatherUseCaseImp
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

    @Provides
    @Singleton
    fun providesWeatherDao(dataBase: WeatherDataBase): WeatherDao = dataBase.weatherDao()

    @Provides
    @Singleton
    fun providesForecastDao(dataBase: WeatherDataBase): ForecastDao = dataBase.forecastDao()

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
    fun provideRoomDataSource(placesDao: PlacesDao, weatherDao: WeatherDao, forecastDao: ForecastDao): LocalDataSource =
        RoomDataSource(placesDao, weatherDao, forecastDao)

    @Provides
    fun providesRetrofitDataSource(api: WeatherApiService): RemoteDataSource =
        RetrofitDataSource(api)

    // ============Domain

    @Provides
    fun provideGetCurrentWeatherUseCase(repository: WeatherRepository): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCaseImp(repository)

    @Provides
    fun provideGetForecastWeatherUseCase(repository: WeatherRepository): GetForecastWeatherUseCase =
        GetForecastWeatherUseCaseImp(repository)

    // todo change it to local data souce
    @Provides
    fun providesChangeUnitSystemUseCase(preferencesHelper: PreferencesHelper): ChangeUnitSystemUseCase =
        ChangeUnitSystemUseCaseImp(preferencesHelper)

    @Provides
    fun getCurrentSystemUseCase(preferencesHelper: PreferencesHelper): GetCurrentUnitSystemUseCase =
        GetCurrentUnitSystemUseCaseImp(preferencesHelper)

    @ApiKey
    @Singleton
    @Provides
    fun provideApiKey() = BuildConfig.API_KEY
}