package com.example.myapplication2202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HorizontalRecycleView(private var data: List<Weather>) :
    RecyclerView.Adapter<HorizontalRecycleView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    fun updateData(newWeatherList: List<Weather>) {
        data = newWeatherList
        notifyItemChanged(3)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val weather = data[position]
        holder.time.text = weather.date
        holder.temperature.text = weather.temperature
        holder.weather.setImageResource(weather.icon)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val weather: ImageView = itemView.findViewById(R.id.weather)
    }
}

