package com.example.finalproject.data.repositories

import com.example.finalproject.data.model.Meal
import com.example.finalproject.data.remote.RetrofitInstance


class MealRepository {

    private val api = RetrofitInstance.api

    suspend fun getMeals(): List<Meal> {

        val response = api.getMeals("")

        if (response.isSuccessful) {

            return response.body()?.meals ?: emptyList()

        } else {

            throw Exception("Failed to load recipes")
        }
    }


    suspend fun getMealDetails(id: String): Meal? {

        val response = api.getMealDetails(id)

        if (response.isSuccessful) {

            return response.body()?.meals?.firstOrNull()

        } else {

            throw Exception("Failed to load recipe details")
        }
    }
}