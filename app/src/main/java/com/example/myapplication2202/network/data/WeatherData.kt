package com.example.myapplication2202.network.data

data class WeatherData(val dt: Long, val main: MainData, val weather: List<WeatherDetail>)

