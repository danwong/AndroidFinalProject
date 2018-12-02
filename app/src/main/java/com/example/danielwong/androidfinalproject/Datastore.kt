package com.example.danielwong.androidfinalproject

import java.util.*
import android.util.Log

data class Feature (
    val id : String? = ""
)

object TheFeatures {
    val FEATURES : MutableList<Feature> = mutableListOf()
}