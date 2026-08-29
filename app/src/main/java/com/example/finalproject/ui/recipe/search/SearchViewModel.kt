package com.example.finalproject.ui.recipe.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.Meal
import com.example.finalproject.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun searchRecipes(query: String) {
        if (query.isBlank()) {
            _meals.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                _loading.value = true

                val response = RetrofitInstance.api.getMeals(query)

                _meals.value = if (response.isSuccessful) {
                    response.body()?.meals ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: Exception) {
                _meals.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}