package com.example.danielwong.androidfinalproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import android.util.Log
import org.json.JSONObject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val CONNECTON_TIMEOUT_MILLISECONDS = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val url = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_hour.geojson"
        GetEarthquakesAsyncTask().execute(url)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    inner class GetEarthquakesAsyncTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            // Before doInBackground
        }

        override fun doInBackground(vararg urls: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val url = URL(urls[0])

                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = CONNECTON_TIMEOUT_MILLISECONDS
                urlConnection.readTimeout = CONNECTON_TIMEOUT_MILLISECONDS

                //var inString = streamToString(urlConnection.inputStream)

                // replaces need for streamToString()
                val inString = urlConnection.inputStream.bufferedReader().readText()
                Log.d("inString", inString)

                publishProgress(inString)
            } catch (ex: Exception) {
                println("HttpURLConnection exception" + ex)
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect()
                }
            }

            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {

//                var weatherData = Gson().fromJson(values[0], ForecastService::class.java)
//                val conditions = weatherData.generateConditions()
//
//
//                tvWeatherInfo.text =
//                        "Location: " + conditions.city + " - " + conditions.country + "\n" +
//                        "Humidity: " + conditions.humidity + "\n" +
//                        "Temperature: " + conditions.temp + "\n" +
//                        "Status: " + conditions.text + "\n" + "GSON!!!"
//
//                val forecasts = weatherData.query.results.channel.item.forecast
//
//                for (next in forecasts) {
//                    println(next)
//                }


            } catch (ex: Exception) {
                println("JSON parsing exception" + ex.printStackTrace())
            }
        }

        override fun onPostExecute(result: String?) {
            // Done
        }
    }
}
