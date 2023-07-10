package com.example.weathermvvm.network.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathermvvm.network.data.MainData
import com.example.weathermvvm.network.data.WeatherData
import com.example.weathermvvm.network.data.WeatherDetail
import com.example.weathermvvm.network.data.WeatherResponse

@Dao
//используемая для указания того, что интерфейс Kotlin является объектом доступа к данным (DAO),
//который используется для взаимодействия с таблицей базы данных
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
//функция используется для вставки данных в таблицу базы данных
    //если есть конфликт с вставляемыми данными, существующие данные должны быть заменены новыми данными
    suspend fun insertMainData(mainData: MainData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherDetail(weatherDetail: WeatherDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherData: WeatherData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherResponse(weatherResponse: WeatherResponse)

    @Query("SELECT * FROM weather_response")
 //используется для выполнения запроса к таблице базы данных.
//SQL-запрос, который необходимо выполнить для таблицы базы данных.
    fun getAllWeatherResponses(): LiveData<List<WeatherResponse>>

}