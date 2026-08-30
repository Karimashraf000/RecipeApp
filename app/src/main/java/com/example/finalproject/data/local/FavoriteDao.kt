package com.example.finalproject.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoriteRecipes")
    fun getAll(): List<FavoriteRecipe>

    @Insert
    fun insertAll(favoriteRecipes: List<FavoriteRecipe>)
    @Delete
    fun delete(favoriteRecipe: FavoriteRecipe)
    @Update
    fun update(favoriteRecipe: FavoriteRecipe)
}