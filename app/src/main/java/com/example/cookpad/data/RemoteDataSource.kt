package com.example.cookpad.data

import com.example.cookpad.data.network.FoodRecipesApi
import com.example.cookpad.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
}