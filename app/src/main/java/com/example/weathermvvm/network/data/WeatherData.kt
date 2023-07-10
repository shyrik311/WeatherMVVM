package com.example.weathermvvm.network.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dt: Long,
    @Embedded val main: MainData,
    @Embedded val weather: List<WeatherDetail>
)

