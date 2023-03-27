package com.example.myapplication2202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollingActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecycleView
    private lateinit var verticalRecycleView: VerticalRecycleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)


        recyclerView = findViewById(R.id.horizontal_recyclerview)
        adapter = HorizontalRecycleView()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

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