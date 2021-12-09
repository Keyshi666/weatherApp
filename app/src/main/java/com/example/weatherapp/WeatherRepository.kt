package com.example.weatherapp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(private val apiService: ApiService) {
    suspend fun getWeatherOfCity(q: String) = apiService.getWeatherOfCity(q)
}