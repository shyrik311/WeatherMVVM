package com.example.weathermvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.network.WeatherRepository
import com.example.weathermvvm.network.data.WeatherData
import kotlinx.coroutines.launch

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherData = MutableLiveData<List<WeatherData>>()
    val weatherData: LiveData<List<WeatherData>> = _weatherData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeatherData() {
        viewModelScope.launch {
            try {
                val weatherResponse = weatherRepository.getWeatherData()
                _weatherData.value = weatherResponse.list
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Failed to fetch weather data"
            }
        }
    }
}