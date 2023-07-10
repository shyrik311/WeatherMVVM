package com.example.weathermvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weathermvvm.network.WeatherRepository
import com.example.weathermvvm.network.data.dao.WeatherDao

class MainViewModelFactory(private val weatherRepository: WeatherRepository, private val weatherDao: WeatherDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(weatherRepository, weatherDao) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}