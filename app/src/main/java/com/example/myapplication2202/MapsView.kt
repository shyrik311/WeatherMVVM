package com.example.myapplication2202

import android.os.Bundle
import android.preference.PreferenceManager
import org.osmdroid.config.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import com.android.volley.Request
import org.osmdroid.views.overlay.Marker
import android.content.Context

class MapsView: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this

        Configuration.getInstance().load(applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext))

        setContentView(R.layout.maps)

        val mapView = findViewById<MapView>(R.id.map_view)
        mapView.setTileSource(TileSourceFactory.MAPNIK)

        mapView.controller.setZoom(10.0)
        mapView.controller.setCenter(GeoPoint(49.34859826052823, 23.511931183506512))

        val lat = 49.34859826052823
        val lng = 23.511931183506512
        val geoPoint = GeoPoint(lat, lng)

        val marker = Marker(mapView)
        marker.position = geoPoint

// Add the marker to the map
        mapView.overlays.add(marker)
        val apiKey = "3b47ed3b92acbc7d2e25a4cc3d1afe02"
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${apiKey}"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Parse the weather data from the JSON response
                val weather = response.getJSONArray("weather").getJSONObject(0)
                val description = weather.getString("description")
                val icon = weather.getString("icon")

                // Display the weather data on the map
                mapView.overlayManager.add(WeatherOverlay(description, icon, GeoPoint(lat, lng)))
            },
            { error ->
                // Handle the error
            }
        )

// Add the API request to the request queue
        Volley.newRequestQueue(this).add(jsonObjectRequest)

    }
}