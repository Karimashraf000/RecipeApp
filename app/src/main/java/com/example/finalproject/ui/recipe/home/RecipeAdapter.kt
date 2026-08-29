package com.example.finalproject.ui.recipe.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.model.Meal

class RecipeAdapter(
    private val meals: List<Meal>,
    private val onItemClick: (Meal) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val recipeImage: ImageView =
            itemView.findViewById(R.id.recipeImage)

        val recipeName: TextView =
            itemView.findViewById(R.id.recipeName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)

        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) {

        val meal = meals[position]

        holder.recipeName.text = meal.strMeal

        Glide.with(holder.itemView.context)
            .load(meal.strMealThumb)
            .into(holder.recipeImage)

        holder.itemView.setOnClickListener {
            onItemClick(meal)
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}