package com.example.finalproject.ui.recipe.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.data.model.Meal

class RecipeAdapter(
    private val onRecipeClick: (Meal) -> Unit,
    private val onFavoriteClick: (Meal, ImageView) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipes = emptyList<Meal>()
    private var favoriteIds = emptySet<String>()

    fun setRecipes(newRecipes: List<Meal>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
    fun setFavoriteIds(ids: Set<String>) {
        favoriteIds = ids
        notifyDataSetChanged()
    }
    inner class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val recipeImage: ImageView =
            itemView.findViewById(R.id.recipeImage)

        private val recipeInfo: TextView =
            itemView.findViewById(R.id.recipeInfo)

        private val recipeName: TextView =
            itemView.findViewById(R.id.recipeName)

        private val favoriteIcon: ImageView =
            itemView.findViewById(R.id.favoriteIcon)
        fun bind(meal: Meal) {

            recipeName.text = meal.strMeal

            recipeInfo.text =
                "${meal.strCategory ?: ""} • ${meal.strArea ?: ""}"

            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .into(recipeImage)

            favoriteIcon.setImageResource(
                if (favoriteIds.contains(meal.idMeal)) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )
            itemView.setOnClickListener {
                onRecipeClick(meal)
            }
            favoriteIcon.setOnClickListener {
                onFavoriteClick(meal, favoriteIcon)
            }
        }
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
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}