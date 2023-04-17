package com.example.myapplication2202

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherResponse>
}

data class WeatherResponse(val list: List<WeatherData>)
data class WeatherData(val dt_txt: String, val main: MainData, val weather: List<WeatherDetail>)
data class MainData(val temp: Double)
data class WeatherDetail(val icon: String)
