package com.example.cookpad.data

import com.example.cookpad.data.database.RecipesDao
import com.example.cookpad.data.database.RecipesEntity
import java.util.concurrent.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    fun readRecipes(): kotlinx.coroutines.flow.Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

}