package com.example.weather.di

import com.example.weather.data.repository.WeatherRepository
import com.example.weather.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRepository(weatherRepository: WeatherRepository): Repository = weatherRepository

}