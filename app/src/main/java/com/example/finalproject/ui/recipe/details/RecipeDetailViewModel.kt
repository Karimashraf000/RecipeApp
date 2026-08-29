package com.example.finalproject.ui.recipe.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.Meal
import com.example.finalproject.data.repositories.MealRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {

    private val repository = MealRepository()

    private val _selectedMeal = MutableLiveData<Meal?>()
    val selectedMeal: LiveData<Meal?> = _selectedMeal

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getMealDetails(id: String) {

        viewModelScope.launch {

            try {
                _loading.value = true

                val meal = repository.getMealDetails(id)

                _selectedMeal.value = meal

            } catch (e: Exception) {

                _error.value = e.message ?: "Failed to load details"

            } finally {
                _loading.value = false
            }
        }
    }
}