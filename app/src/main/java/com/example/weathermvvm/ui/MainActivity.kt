package com.example.weathermvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2202.R
import com.example.weathermvvm.network.WeatherRepository
import com.example.weathermvvm.recycle.HorizontalRecycleView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecycleView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        recyclerView = findViewById(R.id.horizontal_recyclerview)
        adapter = HorizontalRecycleView(emptyList())
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val weatherRepository = WeatherRepository()
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(weatherRepository)
        )[MainViewModel::class.java]
        viewModel.weatherData.observe(this) { weatherData ->
            adapter.updateData(weatherData)
        }

        viewModel.getWeatherData()
    }
}
