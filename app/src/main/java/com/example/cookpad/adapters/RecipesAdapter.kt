package com.example.cookpad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cookpad.adapters.viewholders.RecipesViewHolder
import com.example.cookpad.databinding.RecipesRowLayoutBinding
import com.example.cookpad.models.FoodRecipe
import com.example.cookpad.utils.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesViewHolder>() {
    private var recipe = emptyList<com.example.cookpad.models.Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
        return RecipesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentResult = recipe[position]
        holder.bind(currentResult)
    }

    fun setData(newData: FoodRecipe) {
        val recipesDiffUtil = RecipesDiffUtil(recipe, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipe = newData.results
        diffUtilResult.dispatchUpdatesTo(this)


    }
}