package com.example.myapplication2202.ui

import com.example.myapplication2202.network.data.WeatherData
import com.example.myapplication2202.network.data.WeatherResponse

interface MainContract {
    interface View {
        fun showWeatherData(weatherData: List<WeatherData>)
        fun showError(message: String)
    }

    interface Presenter{
        fun getWeatherData()
        fun onDestroy()
    }

    interface Model{
        suspend fun getWeatherData(): WeatherResponse
    }
}