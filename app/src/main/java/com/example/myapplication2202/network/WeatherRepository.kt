package com.example.myapplication2202.network

import com.example.myapplication2202.network.data.WeatherResponse
import retrofit2.HttpException
import retrofit2.Response

class WeatherRepository{
    private val weatherService: WeatherService = WeatherServiceFactory.create()

     suspend fun getWeatherData(): WeatherResponse {

        val response: Response<WeatherResponse> = weatherService.getWeatherForecast(
            "Kyiv",
            "3b47ed3b92acbc7d2e25a4cc3d1afe02",
            "metric"
        )
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw HttpException(response)
        }
    }
}
