package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig.API_KEY
import com.example.weatherapp.BuildConfig.BASE_URL
import com.example.weatherapp.data.local.PreferencesHelper
import com.example.weatherapp.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private const val PARAMETER_APP_ID = "appid"
    private const val PARAMETER_UNITS = "units"

    @Provides
    fun provideApiKeyInterceptor(preferencesHelper: PreferencesHelper,
                                 @ApiKey API_KEY: String) = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter(PARAMETER_APP_ID, API_KEY)
                .addQueryParameter(PARAMETER_UNITS, preferencesHelper.getSystemUnits())
                .build()

            val newRequest = originalRequest.newBuilder().url(newUrl).build()

            return chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(interceptor)
            .build()


    @Provides
    fun provideFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun buildRetrofit(client: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    fun buildWeatherApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)
}