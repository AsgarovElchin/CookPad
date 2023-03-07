package com.example.cookpad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookpad.viewmodels.MainViewModel
import com.example.cookpad.adapters.RecipesAdapter
import com.example.cookpad.databinding.FragmentRecipesBinding
import com.example.cookpad.utils.Constants.API_KEY
import com.example.cookpad.utils.NetworkResult
import com.example.cookpad.viewmodels.RecipesViewModel


class RecipesFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var binding: FragmentRecipesBinding
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root


        setupRecyclerView()
        requestApiData()

    }
    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }

}




