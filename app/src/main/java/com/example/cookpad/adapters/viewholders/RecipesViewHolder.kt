package com.example.cookpad.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookpad.R
import com.example.cookpad.databinding.RecipesRowLayoutBinding

class RecipesViewHolder(val binding: RecipesRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
  fun bind(result: com.example.cookpad.models.Result){
      binding.titleTextView.text = result.title
      binding.descriptionTextView.text = result.summary
      binding.recipeImageView.load(result.sourceUrl)
      binding.heartTextView.text = result.aggregateLikes.toString()
      binding.clockTextView.text = result.readyInMinutes.toString()

  }
    fun applyVeganColor(view: View,vegan:Boolean){
        if(vegan){
            when(view){
                is TextView ->{
                    view.setTextColor(ContextCompat.getColor(view.context,R.color.green))
                }
                is ImageView->{
                    view.setColorFilter(ContextCompat.getColor(view.context,R.color.green))
                }
            }
        }
    }

    }
