package com.example.myapplication2202

import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

object WeatherUtils {
    private fun getDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(date)
    }

    private fun getIconId(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.sun
            "01n" -> R.drawable.cloud
            "02d" -> R.drawable.moon
            "02n" -> R.drawable.rain_drops
            "03d", "03n", "04d", "04n" -> R.drawable.cloudy_night
            "09d", "09n" -> R.drawable.raindrops
            "10d", "10n" -> R.drawable.raining
            "11d", "11n" -> R.drawable.thunderbolt
            "13d", "13n" -> R.drawable.cloudy_night
            "50d", "50n" -> R.drawable.clouds
            else -> R.drawable.sun
        }
    }

    fun parseWeatherResponse(response: String): List<Weather> {
        val weatherList = mutableListOf<Weather>()
        try {
            val jsonObject = JSONObject(response)
            val jsonArray = jsonObject.getJSONArray("list")
            for (i in 0 until jsonArray.length()) {
                val hourObject = jsonArray.optJSONObject(i)
                val date = getDate(hourObject.getLong("dt"))
                val temperature = hourObject.getJSONObject("main").getString("temp")
                val weatherArray = hourObject.getJSONArray("weather")
                val weatherObject = weatherArray.getJSONObject(0)
                val main = weatherObject.getString("main")
                val iconId = getIconId(weatherObject.getString("icon"))
                val weather = Weather(date, "$temperature°C", iconId, main)
                weatherList.add(weather)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return weatherList
    }
}