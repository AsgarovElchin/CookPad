package com.example.cookpad.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookpad.R
import com.example.cookpad.adapters.IngredientsAdapter
import com.example.cookpad.databinding.FragmentIngredientsBinding


class IngredientsFragment : Fragment() {

    private val ingredientAdapter : IngredientsAdapter by lazy { IngredientsAdapter() }

    private lateinit var binding :FragmentIngredientsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        val myBundle: com.example.cookpad.models.Result? = args?.getParcelable("recipeBundle")
        setupIngredientsRecyclerView()
        myBundle?.extendedIngredients?.let {
            ingredientAdapter.setData(it)
        }

    }

    private fun setupIngredientsRecyclerView(){
        binding.ingredientsRecyclerview.adapter = ingredientAdapter
        binding.ingredientsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }


}