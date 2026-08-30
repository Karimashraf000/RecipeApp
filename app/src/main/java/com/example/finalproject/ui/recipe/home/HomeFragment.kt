package com.example.finalproject.ui.recipe.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.local.AppDatabase
import com.example.finalproject.data.local.FavoriteRecipe
import com.example.finalproject.data.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    override fun onResume() {
        super.onResume()
        if (::recipeAdapter.isInitialized) {
            loadFavoriteIds()
        }
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(
            onRecipeClick = { meal ->

                val bundle = bundleOf("mealId" to meal.idMeal)

                findNavController().navigate(
                    R.id.action_home_to_detail,
                    bundle
                )
            },
            onFavoriteClick = { meal, icon ->
                toggleFavorite(meal, icon)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recipeAdapter
    }
    private fun getCurrentUserEmail(): String {
        val prefs = requireContext().getSharedPreferences(
            "RecipeAppPreferences",
            android.content.Context.MODE_PRIVATE
        )
        return prefs.getString("currentUserEmail", "") ?: ""
    }
    private fun toggleFavorite(meal: Meal, icon: ImageView) {
        val dao = AppDatabase.getDatabase(requireContext()).favoriteDao()
        val userEmail = getCurrentUserEmail()
        lifecycleScope.launch(Dispatchers.IO) {
            val isFavorite = dao.getAll(userEmail).any { it.mealId == meal.idMeal }

            if (isFavorite) {
                val favorite = dao.getAll(userEmail).first { it.mealId == meal.idMeal }
                dao.delete(favorite)
            } else {
                dao.insertAll(
                    listOf(
                        FavoriteRecipe(
                            mealId = meal.idMeal ?: "",
                            mealName = meal.strMeal ?: "",
                            mealThumb = meal.strMealThumb ?: "",
                            userEmail = getCurrentUserEmail()
                        )
                    )
                )
            }

            withContext(Dispatchers.Main) {
                icon.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite_border
                    else R.drawable.ic_favorite
                )
            }
            loadFavoriteIds()
        }
    }
    private fun loadFavoriteIds() {
        val dao = AppDatabase.getDatabase(requireContext()).favoriteDao()
        val userEmail = getCurrentUserEmail()
        lifecycleScope.launch(Dispatchers.IO) {
            val ids = dao.getAll(userEmail).map { it.mealId }.toSet()

            withContext(Dispatchers.Main) {
                recipeAdapter.setFavoriteIds(ids)
            }
        }
    }
    private fun observeViewModel() {

        viewModel.meals.observe(viewLifecycleOwner) { meals ->
            recipeAdapter.setRecipes(meals)
            loadFavoriteIds()
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