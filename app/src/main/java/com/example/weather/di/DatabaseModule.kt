package com.example.weather.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.weather.data.localdatabase.WeatherDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    private const val databaseName = "WeatherDatabase"
    private const val preferencesName = "WeatherPreference"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        WeatherDb::class.java,
        databaseName
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDb: WeatherDb) = weatherDb.weatherDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(preferencesName, 0)
}