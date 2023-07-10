package com.example.weathermvvm.network.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_detail")
data class WeatherDetail(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val icon: String
)
