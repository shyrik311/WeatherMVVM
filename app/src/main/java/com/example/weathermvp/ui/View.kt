package com.example.weathermvp.ui

import com.example.weathermvp.network.data.WeatherData


interface View {
    fun showWeatherData(weatherData: List<WeatherData>)
    fun showError(message: String)
}

