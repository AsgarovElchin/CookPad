package com.example.cookpad.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookpad.databinding.IngredientsRowLayoutBinding
import com.example.cookpad.models.ExtendedIngredient
import com.example.cookpad.utils.Constants.BASE_IMAGE_URL

class IngredientsViewHolder(val binding: IngredientsRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(ingredients: ExtendedIngredient) {
        binding.ingredientImageView.load(BASE_IMAGE_URL+ingredients.image)
        binding.ingredientName.text = ingredients.name.capitalize()
        binding.ingredientAmount.text = ingredients.amount.toString()
        binding.ingredientUnit.text = ingredients.unit
        binding.ingredientConsistency.text = ingredients.consistency
        binding.ingredientOriginal.text = ingredients.original
    }
}