package com.example.finalproject.data.remote

import com.example.finalproject.data.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("search.php")
    suspend fun getMeals(
        @Query("s") query: String
    ): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") id: String
    ): Response<MealResponse>
}