package com.example.finalproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteRecipes")
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mealId: String,
    val mealName: String,
    val mealThumb: String
)
