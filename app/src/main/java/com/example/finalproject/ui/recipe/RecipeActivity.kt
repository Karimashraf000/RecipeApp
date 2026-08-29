package com.example.finalproject.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.ui.recipe.favorite.FavoriteFragment
import com.example.finalproject.ui.recipe.home.HomeFragment
import com.example.finalproject.ui.recipe.search.SearchFragment

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recipe)

        val bottomNavigation =
            findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                R.id.bottomNavigation
            )

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.recipeContainer,
                    HomeFragment()
                )
                .commit()
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
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {

        menuInflater.inflate(
            R.menu.menu_recipe,
            menu
        )

        return true
    }
    override fun onOptionsItemSelected(
        item: android.view.MenuItem
    ): Boolean {

        return when (item.itemId) {

            R.id.action_sign_out -> {

                signOut()
                true
            }

            R.id.action_about -> {

                openFragment(
                    com.example.finalproject.ui.about.AboutFragment()
                )

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFragment(fragment: androidx.fragment.app.Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.recipeContainer,
                fragment
            )
            .commit()
    }

}