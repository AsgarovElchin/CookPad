package com.example.cookpad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cookpad.adapters.viewholders.IngredientsViewHolder
import com.example.cookpad.adapters.viewholders.RecipesViewHolder
import com.example.cookpad.databinding.IngredientsRowLayoutBinding
import com.example.cookpad.databinding.RecipesRowLayoutBinding
import com.example.cookpad.models.ExtendedIngredient
import com.example.cookpad.utils.RecipesDiffUtil

class IngredientsAdapter:RecyclerView.Adapter<IngredientsViewHolder>() {
    private var extendedIngredient = emptyList<com.example.cookpad.models.ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return extendedIngredient.size
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentResult = extendedIngredient[position]
        holder.bind(currentResult)
    }

    fun setData(newIngredients:List<ExtendedIngredient>){
        val ingredientDiffUtil = RecipesDiffUtil(extendedIngredient,newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientDiffUtil)
        extendedIngredient = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }
}