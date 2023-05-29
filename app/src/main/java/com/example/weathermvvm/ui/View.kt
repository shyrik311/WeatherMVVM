package com.example.weathermvvm.ui

import com.example.weathermvvm.network.data.WeatherData

interface View {
    fun showWeatherData(weatherData: List<WeatherData>)
    fun showError(message: String)
}

