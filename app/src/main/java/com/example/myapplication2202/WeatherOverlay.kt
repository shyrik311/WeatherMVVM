package com.example.myapplication2202

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.util.GeoPoint


class WeatherOverlay(private val description: String, private val icon: String,private val geoPoint: GeoPoint): Overlay() {

    override fun draw(c: Canvas, mapView: MapView?, shadow: Boolean) {
        // Convert the GeoPoint to a screen coordinate
        val projection = mapView?.projection ?: return
        val point = projection.toPixels(geoPoint, null)

        Glide.with(mapView.context)
            .load("https://openweathermap.org/img/w/${icon}.png")
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    // Draw the weather icon on the map
                    val size = 100
                    val halfSize = size / 2
                    val left = point.x - halfSize
                    val top = point.y - halfSize
                    val right = point.x + halfSize
                    val bottom = point.y + halfSize
                    resource.setBounds(left, top, right, bottom)
                    resource.draw(c)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Do nothing
                }
            })

        // Draw the weather description on the map
        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 30f
        }
        c.drawText(description, point.x.toFloat(), (point.y - 100).toFloat(), paint)
    }
}
