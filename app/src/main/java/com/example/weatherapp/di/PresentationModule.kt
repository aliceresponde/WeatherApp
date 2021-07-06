package com.example.weatherapp.di

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
}