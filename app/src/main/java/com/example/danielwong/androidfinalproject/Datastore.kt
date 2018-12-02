package com.example.danielwong.androidfinalproject

import java.util.*
import android.util.Log
import org.json.JSONArray

data class Earthquake (
    val mag: Double? = null,
    val place: String ="",
    val id: String? = "",
    val time: Double? = null,
    val title: String? ="",
    val latitude: Double? = null,
    val longitude: Double? = null
)

data class CompleteJson(val features : List<Feature>) {
    fun generateFeatures(): List<Earthquake> {
        for (f in features) {
            Log.d("lat", f.geometry.coordinates[0].toString())
            val temp = Earthquake(
                f.properties.mag,
                f.properties.place,
                f.id,
                f.properties.time,
                f.properties.title,
                f.geometry.coordinates[0],
                f.geometry.coordinates[1]

            )
            TheFeatures.earthquakes.add(temp)
        }
        return TheFeatures.earthquakes
    }
}

object TheFeatures {
    val earthquakes : MutableList<Earthquake> = mutableListOf()
}

data class Feature(val properties: Properties, val id:String?, val geometry: Geometry)
data class Properties(val place: String, val mag: Double, val time: Double?, val title: String)
data class Geometry(val coordinates: Array<Double>)
