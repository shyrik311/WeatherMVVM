package com.example.weathermvvm.network.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_data")
data class MainData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val temp: Double)
