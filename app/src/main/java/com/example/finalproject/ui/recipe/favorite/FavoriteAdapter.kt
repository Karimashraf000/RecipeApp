package com.example.finalproject.ui.recipe.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.data.local.FavoriteRecipe

class FavoriteAdapter(
    private val favorites: MutableList<FavoriteRecipe>,
    private val onItemClick: (FavoriteRecipe) -> Unit,
    private val onRemoveClick: (FavoriteRecipe) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumb: ImageView = itemView.findViewById(R.id.recipeImage)
        val txtName: TextView = itemView.findViewById(R.id.recipeName)
        val txtInfo: TextView = itemView.findViewById(R.id.recipeInfo)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]

        holder.txtName.text = favorite.mealName
        holder.txtInfo.visibility = View.GONE // no matching data for this field

        Glide.with(holder.itemView.context)
            .load(favorite.mealThumb)
            .into(holder.imgThumb)

        // Everything in this list is already a favorite, so show the filled heart
        holder.favoriteIcon.setImageResource(R.drawable.ic_favorite)
        holder.favoriteIcon.setOnClickListener {
            onRemoveClick(favorite)
        }
        holder.itemView.setOnClickListener {
            onItemClick(favorite)
        }
    }

    override fun getItemCount(): Int = favorites.size

    fun removeItem(favorite: FavoriteRecipe) {
        val index = favorites.indexOf(favorite)
        if (index != -1) {
            favorites.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}