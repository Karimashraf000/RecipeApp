
package com.example.finalproject.ui.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.R
import com.example.finalproject.ui.auth.AuthActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val toolbar = findViewById<Toolbar>(R.id.recipeToolbar)
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->

            val navOptions = androidx.navigation.NavOptions.Builder()
                .setPopUpTo(navController.graph.startDestinationId, false)
                .setLaunchSingleTop(true)
                .build()

            when (item.itemId) {

                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment, null, navOptions)
                    true
                }

                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment, null, navOptions)
                    true
                }

                R.id.favoriteFragment -> {
                    navController.navigate(R.id.favoriteFragment, null, navOptions)
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
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
                navHostFragment.navController.navigate(R.id.aboutFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
