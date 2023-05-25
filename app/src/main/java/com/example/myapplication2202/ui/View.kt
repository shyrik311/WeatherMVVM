package com.example.myapplication2202.ui

import com.example.myapplication2202.network.data.WeatherData


interface View {
    fun showWeatherData(weatherData: List<WeatherData>)
    fun showError(message: String)
}

