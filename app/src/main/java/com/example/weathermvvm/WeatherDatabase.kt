package com.example.weathermvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weathermvvm.network.data.MainData
import com.example.weathermvvm.network.data.WeatherData
import com.example.weathermvvm.network.data.WeatherDetail
import com.example.weathermvvm.network.data.WeatherResponse
import com.example.weathermvvm.network.data.dao.WeatherDao

@Database(//класс является классом базы данных.
    entities = [MainData::class, WeatherDetail::class, WeatherData::class, WeatherResponse::class],
 //объекты (модели данных), которые будут храниться в базе данных
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDataBase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}