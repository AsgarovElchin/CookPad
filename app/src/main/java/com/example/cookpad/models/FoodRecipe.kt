package com.example.cookpad.models


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @SerializedName("results")
    val results: List<Result>
)