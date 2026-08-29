package com.example.finalproject.ui.recipe.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.data.model.Meal

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeDetailViewModel by viewModels()

    private lateinit var detailImage: ImageView
    private lateinit var detailName: TextView
    private lateinit var detailCategory: TextView
    private lateinit var detailArea: TextView
    private lateinit var detailInstructions: TextView
    private lateinit var showMoreButton: TextView
    private lateinit var watchVideoButton: Button

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        detailImage = view.findViewById(R.id.detailImage)
        detailName = view.findViewById(R.id.detailName)
        detailCategory = view.findViewById(R.id.detailCategory)
        detailArea = view.findViewById(R.id.detailArea)
        detailInstructions = view.findViewById(R.id.detailInstructions)
        showMoreButton = view.findViewById(R.id.showMoreButton)
        watchVideoButton = view.findViewById(R.id.watchVideoButton)

        val mealId = arguments?.getString("mealId")

        if (mealId == null) {
            Toast.makeText(requireContext(), "Recipe not found", Toast.LENGTH_SHORT).show()
            return
        }

        observeViewModel()

        viewModel.getMealDetails(mealId)
    }

    private fun observeViewModel() {

        viewModel.selectedMeal.observe(viewLifecycleOwner) { meal ->
            meal?.let { displayMeal(it) }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayMeal(meal: Meal) {

        detailName.text = meal.strMeal
        detailCategory.text = meal.strCategory
        detailArea.text = meal.strArea
        detailInstructions.text = meal.strInstructions ?: "No instructions available"

        Glide.with(requireContext())
            .load(meal.strMealThumb)
            .into(detailImage)

        setupExpand()
        setupVideo(meal)
    }

    private fun setupExpand() {

        detailInstructions.maxLines = 8

        showMoreButton.setOnClickListener {
            detailInstructions.maxLines = Int.MAX_VALUE
            showMoreButton.visibility = View.GONE
        }
    }

    private fun setupVideo(meal: Meal) {

        if (meal.strYoutube.isNullOrBlank()) {
            watchVideoButton.visibility = View.GONE
            return
        }

        watchVideoButton.visibility = View.VISIBLE

        watchVideoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))
            startActivity(intent)
        }
    }
}