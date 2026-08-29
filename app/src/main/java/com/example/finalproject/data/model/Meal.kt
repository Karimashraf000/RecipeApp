package com.example.finalproject.data.model

data class Meal(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strYoutube: String?
)

data class MealResponse(
    val meals: List<Meal>?
)