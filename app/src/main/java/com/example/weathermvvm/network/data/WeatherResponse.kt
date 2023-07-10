package com.example.weathermvvm.network.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_response")
// аннотация, используемая для указания того, что класс Kotlin представляет сущность в таблице базы данных
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    //аннотация, используемая для указания того, что свойство является первичным ключом таблицы
    @Embedded val list: List<WeatherData>)
//используемая для указания того, что свойство является внедренным объектом
