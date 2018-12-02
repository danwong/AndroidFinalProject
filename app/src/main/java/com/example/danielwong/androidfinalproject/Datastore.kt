package com.example.danielwong.androidfinalproject

import java.util.*
import android.util.Log

data class Earthquake (
    val place: String ="",
    val id: String? = ""
)

data class CompleteJson(val features : List<Feature>) {
    fun generateFeatures(): List<Earthquake> {
        for (f in features) {
            val temp = Earthquake(
                f.properties.place,
                f.id


            )
            TheFeatures.earthquakes.add(temp)
        }
        return TheFeatures.earthquakes
    }
}

object TheFeatures {
    val earthquakes : MutableList<Earthquake> = mutableListOf()
}

data class Feature(val properties: Properties, val id:String?)
data class Properties(val place: String)
