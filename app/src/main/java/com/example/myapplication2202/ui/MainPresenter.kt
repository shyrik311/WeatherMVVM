package com.example.myapplication2202.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainContract.View,
    private val model: MainContract.Model
) : MainContract.Presenter {
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

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}