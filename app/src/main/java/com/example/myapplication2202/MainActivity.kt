package com.example.myapplication2202

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2202.network.WeatherRepository
import com.example.myapplication2202.recycle.HorizontalRecycleView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var weatherRepository: WeatherRepository = WeatherRepository()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecycleView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        recyclerView = findViewById(R.id.horizontal_recyclerview)
        adapter = HorizontalRecycleView(emptyList())
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val weatherResponse = weatherRepository.getWeatherData()
                adapter.updateData(weatherResponse.list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
