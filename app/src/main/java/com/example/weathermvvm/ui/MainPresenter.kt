package com.example.weathermvp.ui

import com.example.weathermvp.network.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: View,
    private val model: WeatherRepository

) : Presenter {
    override fun getWeatherData() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val weatherResponse = model.getWeatherData()
                view.showWeatherData(weatherResponse.list)
            }catch (e: Exception) {
                e.printStackTrace()
                view.showError("Failed to fetch weather data")
            }
        }
    }
}