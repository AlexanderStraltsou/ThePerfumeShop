package com.example.theperfumeshop


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Perfume(
    val name: String,
    val price: Double,
    val imageResId: Int
) : Parcelable
