package com.example.cookpad.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookpad.models.FoodRecipe
import com.example.cookpad.utils.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}