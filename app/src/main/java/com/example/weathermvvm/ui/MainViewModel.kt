package com.example.weathermvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvvm.network.WeatherRepository
import com.example.weathermvvm.network.data.WeatherData
import com.example.weathermvvm.network.data.WeatherResponse
import com.example.weathermvvm.network.data.dao.WeatherDao
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository,
    private val weatherDao: WeatherDao
    ) : ViewModel() {

    private val _weatherData = MutableLiveData<List<WeatherData>>()
    val weatherData: LiveData<List<WeatherData>> = _weatherData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private fun  saveWeatherDataToDataBase(weatherDataList: List<WeatherData>) {
        val weatherResponse = WeatherResponse(list = weatherDataList)
        viewModelScope.launch {
            weatherDao.insertWeatherResponse(weatherResponse)
        }
    }

    fun getWeatherData() {
        viewModelScope.launch {
            try {
                val weatherResponse = weatherRepository.getWeatherData()
                _weatherData.value = weatherResponse.list
                saveWeatherDataToDataBase(weatherResponse.list)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Failed to fetch weather data"
            }
        }
    }
}