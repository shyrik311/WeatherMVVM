package com.example.myapplication2202.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2202.R
import com.example.myapplication2202.network.WeatherRepository
import com.example.myapplication2202.network.data.WeatherData
import com.example.myapplication2202.recycle.HorizontalRecycleView

class MainActivity : AppCompatActivity(), View {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecycleView
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        recyclerView = findViewById(R.id.horizontal_recyclerview)
        adapter = HorizontalRecycleView(emptyList())
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        presenter = MainPresenter(this, WeatherRepository())
        presenter.getWeatherData()


    }

    override fun showWeatherData(weatherData: List<WeatherData>) {
        adapter.updateData(weatherData)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
