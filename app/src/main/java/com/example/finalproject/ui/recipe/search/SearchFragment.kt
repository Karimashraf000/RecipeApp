package com.example.finalproject.ui.recipe.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
import com.example.finalproject.ui.recipe.home.RecipeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchEditText = view.findViewById<EditText>(R.id.searchEditText)
        val searchButton = view.findViewById<Button>(R.id.searchButton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchRecyclerView)
        val progressBar = view.findViewById<ProgressBar>(R.id.searchProgressBar)

        recipeAdapter = RecipeAdapter(
            onRecipeClick = { meal ->
                val bundle = bundleOf("mealId" to meal.idMeal)
                findNavController().navigate(R.id.action_search_to_detail, bundle)
            },
            onFavoriteClick = { meal, icon ->
                toggleFavorite(meal, icon)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recipeAdapter

        searchButton.setOnClickListener {

            val query =
                searchEditText.text.toString().trim().lowercase()

            if (query.isEmpty()) {
                Toast.makeText(requireContext(), "Enter a recipe name", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchRecipes(query)
            }
        }

        viewModel.meals.observe(viewLifecycleOwner) { meals ->
            recipeAdapter.setRecipes(meals)
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
    override fun onResume() {
        super.onResume()
        if (::recipeAdapter.isInitialized) {
            loadFavoriteIds()
        }
    }
    private fun toggleFavorite(meal: Meal, icon: ImageView) {
        val dao = AppDatabase.getDatabase(requireContext()).favoriteDao()

        lifecycleScope.launch(Dispatchers.IO) {
            val isFavorite = dao.getAll().any { it.mealId == meal.idMeal }

            if (isFavorite) {
                val favorite = dao.getAll().first { it.mealId == meal.idMeal }
                dao.delete(favorite)
            } else {
                dao.insertAll(
                    listOf(
                        FavoriteRecipe(
                            mealId = meal.idMeal ?: "",
                            mealName = meal.strMeal ?: "",
                            mealThumb = meal.strMealThumb ?: ""
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

        lifecycleScope.launch(Dispatchers.IO) {
            val ids = dao.getAll().map { it.mealId }.toSet()

            withContext(Dispatchers.Main) {
                recipeAdapter.setFavoriteIds(ids)
            }
        }
    }
}