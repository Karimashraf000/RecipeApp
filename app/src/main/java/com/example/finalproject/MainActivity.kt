package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//import com.example.finalproject.ui.recipe.RecipeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addRecipeButton = findViewById<Button>(R.id.addRecipeButton)
        val viewRecipesButton = findViewById<Button>(R.id.viewRecipesButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        addRecipeButton.setOnClickListener {
            // Add Recipe action will be added later
        }

        viewRecipesButton.setOnClickListener {
//            startActivity(Intent(this, RecipeActivity::class.java))
        }

        logoutButton.setOnClickListener {
            // Logout action will be added later
        }
    }
}