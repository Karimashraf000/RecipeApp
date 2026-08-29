package com.example.finalproject.data.remote

import com.example.finalproject.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") name: String
    ): MealResponse
}