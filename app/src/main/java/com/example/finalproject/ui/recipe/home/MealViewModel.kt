package com.example.finalproject.ui.recipe.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.model.Meal
import com.example.finalproject.data.repositories.MealRepository
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {

    private val repository = MealRepository()

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    private val _selectedMeal = MutableLiveData<Meal?>()
    val selectedMeal: LiveData<Meal?> = _selectedMeal

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getMeals() {

        viewModelScope.launch {

            try {

                _loading.value = true

                val meals = repository.getMeals()

                _meals.value = meals

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Something went wrong"

            } finally {

                _loading.value = false
            }
        }
    }


    fun getMealDetails(id: String) {

        viewModelScope.launch {

            try {

                _loading.value = true

                val meal =
                    repository.getMealDetails(id)

                _selectedMeal.value = meal

            } catch (e: Exception) {

                _error.value =
                    e.message ?: "Something went wrong"

            } finally {

                _loading.value = false
            }
        }
    }
}