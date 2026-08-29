package com.example.finalproject.ui.recipe.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.ui.recipe.home.RecipeAdapter

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_search,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val searchEditText =
            view.findViewById<EditText>(R.id.searchEditText)

        val searchButton =
            view.findViewById<Button>(R.id.searchButton)

        val recyclerView =
            view.findViewById<RecyclerView>(
                R.id.searchRecyclerView
            )

        val progressBar =
            view.findViewById<ProgressBar>(
                R.id.searchProgressBar
            )

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        searchButton.setOnClickListener {

            val query =
                searchEditText.text.toString().trim()

            if (query.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Enter a recipe name",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                viewModel.searchRecipes(query)
            }
        }

        viewModel.meals.observe(viewLifecycleOwner) { meals ->

            val adapter = RecipeAdapter(meals) { meal ->

                // Navigation to RecipeDetailFragment
                val bundle = Bundle()

                bundle.putString(
                    "mealId",
                    meal.idMeal
                )

                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.recipeContainer,
                        com.example.finalproject.ui.recipe.details.RecipeDetailFragment()
                    )
                    .addToBackStack(null)
                    .commit()
            }

            recyclerView.adapter = adapter
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->

            progressBar.visibility =
                if (loading) View.VISIBLE
                else View.GONE
        }
    }
}