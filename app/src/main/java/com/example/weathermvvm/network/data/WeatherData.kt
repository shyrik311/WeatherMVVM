package com.example.weathermvvm.network.data

data class WeatherData(val dt: Long, val main: MainData, val weather: List<WeatherDetail>)

