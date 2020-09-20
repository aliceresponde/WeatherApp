package com.example.weatherapp.di

import com.example.weatherapp.domain.usecases.SaveMarkerUseCase
import com.example.weatherapp.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
object PresentationModule {
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesHomeViewModel(
        coroutineDispatcher: CoroutineDispatcher,
        saveMarkerUseCase: SaveMarkerUseCase
    ) = HomeViewModel(coroutineDispatcher, saveMarkerUseCase)

}