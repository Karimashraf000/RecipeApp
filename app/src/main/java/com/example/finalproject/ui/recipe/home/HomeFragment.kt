package com.example.finalproject.ui.recipe.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MealViewModel by viewModels()

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recipesRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        setupRecyclerView()
        observeViewModel()

        viewModel.getMeals()
    }

    private fun setupRecyclerView() {

        recipeAdapter = RecipeAdapter { meal ->

            val bundle = bundleOf("mealId" to meal.idMeal)

            findNavController().navigate(
                R.id.action_home_to_detail,
                bundle
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recipeAdapter
    }

    private fun observeViewModel() {

        viewModel.meals.observe(viewLifecycleOwner) { meals ->
            recipeAdapter.setRecipes(meals)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}