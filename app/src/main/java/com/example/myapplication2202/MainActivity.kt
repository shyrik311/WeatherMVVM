package com.example.myapplication2202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecycleView
    private lateinit var verticalRecycleView: VerticalRecycleView

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

//        val weatherList = listOf(
//            Weather("Mon", "10°C", R.drawable.cloud),
//            Weather("Tue", "8°C", R.drawable.sun),
//            Weather("Wed", "6°C", R.drawable.rain_drops),
//            Weather("Thu", "12°C", R.drawable.sun),
//            Weather("Fri", "14°C", R.drawable.sun)
//        )

        recyclerView = findViewById(R.id.horizontal_recyclerview)
        adapter = HorizontalRecycleView(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val weatherResponse = weatherRepository.getWeatherData()
                val weatherList = WeatherUtils.parseWeatherResponse(Gson().toJson(weatherResponse))
                adapter.updateData(weatherList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        verticalRecycleView = VerticalRecycleView()
        recyclerView = findViewById(R.id.vertical_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = verticalRecycleView

        val buttonMap = findViewById<Button>(R.id.map)
        buttonMap.setOnClickListener {
            val intent = Intent(this, MapsView::class.java )
            startActivity(intent)
        }
    }
}
