package com.example.cookpad.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookpad.data.DataStoreRepository
import com.example.cookpad.utils.Constants
import com.example.cookpad.utils.Constants.API_KEY
import com.example.cookpad.utils.Constants.DEFAULT_DIET_TYPE
import com.example.cookpad.utils.Constants.DEFAULT_MEAL_TYPE
import com.example.cookpad.utils.Constants.DEFAULT_RECIPES_NUMBER
import com.example.cookpad.utils.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.cookpad.utils.Constants.QUERY_API_KEY
import com.example.cookpad.utils.Constants.QUERY_DIET
import com.example.cookpad.utils.Constants.QUERY_FILL_INGREDIENTS
import com.example.cookpad.utils.Constants.QUERY_NUMBER
import com.example.cookpad.utils.Constants.QUERY_SEARCH
import com.example.cookpad.utils.Constants.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(application: Application, private val dataStoreRepository: DataStoreRepository) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    fun saveMealAndDietType(mealType:String,mealTypeId:Int, dietType:String, dietTypeId:Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

    fun applySearchQuery(searchQuery:String):HashMap<String,String>{
        val queries : HashMap<String,String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        viewModelScope.launch {
            readMealAndDietType.collect{value->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }
}