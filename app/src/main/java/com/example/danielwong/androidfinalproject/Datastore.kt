package com.example.danielwong.androidfinalproject

import java.util.*
import android.util.Log

data class Feature (
    val id : String? = ""
)

data class CompleteJson(val features : List<Feature>) {
    fun generateFeatures(): List<Feature> {
        for (f in features) {
            val temp = Feature(
                f.id
            )
            TheFeatures.FEATURES.add(temp)
        }
        return TheFeatures.FEATURES
    }
}

object TheFeatures {
    val FEATURES : MutableList<Feature> = mutableListOf()
}