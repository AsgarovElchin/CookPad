package com.example.cookpad.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookpad.R
import com.example.cookpad.databinding.RecipesRowLayoutBinding
import com.example.cookpad.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

class RecipesViewHolder(val binding: RecipesRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
  fun bind(result: com.example.cookpad.models.Result){
      binding.titleTextView.text = result.title
      result.summary.let {
          val summary = Jsoup.parse(it).text()
          binding.descriptionTextView.text = summary
      }
      binding.recipeImageView.load(result.image)
      binding.heartTextView.text = result.aggregateLikes.toString()
      binding.clockTextView.text = result.readyInMinutes.toString()
      applyVeganColor(binding.leafTextView,result.vegan)
      applyVeganColor(binding.leafImageView,result.vegan)
      binding.root.setOnClickListener {
          val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
          binding.recipesRowLayout.findNavController().navigate(action)
      }

  }
    fun applyVeganColor(view: View, vegan:Boolean){
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
