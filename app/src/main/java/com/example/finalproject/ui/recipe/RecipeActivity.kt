package com.example.finalproject.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.R
import com.example.finalproject.ui.auth.AuthActivity
import com.example.finalproject.ui.recipe.favorite.FavoriteFragment
import com.example.finalproject.ui.recipe.home.HomeFragment
import com.example.finalproject.ui.recipe.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottomNavigation)

        if (savedInstanceState == null) {
            openFragment(HomeFragment())
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.searchFragment -> {
                    openFragment(SearchFragment())
                    true
                }
                R.id.favoriteFragment -> {
                    openFragment(FavoriteFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recipe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sign_out -> {
                signOut()
                true
            }
            R.id.action_about -> {
                openFragment(com.example.finalproject.ui.about.AboutFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // بتفتح فراجمنت جديد فوق الـ container، مع دعم زر الرجوع لصفحة التفاصيل
    fun openFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.recipeContainer, fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }

    private fun signOut() {
        val sharedPreferences =
            getSharedPreferences("RecipeAppPreferences", Context.MODE_PRIVATE)

        sharedPreferences.edit()
            .putBoolean("isLoggedIn", false)
            .apply()

        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}