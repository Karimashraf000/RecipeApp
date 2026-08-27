package com.example.finalproject.ui.auth

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.ui.auth.LoginFragment
import com.example.finalproject.R
import com.example.finalproject.ui.auth.RegisterFragment
import com.example.finalproject.ui.auth.SplashFragment

class AuthActivity : AppCompatActivity() {

    // SharedPreferences is used to save the user's login status
    private lateinit var sharedPreferences: SharedPreferences

    // This function runs when AuthActivity starts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Connect AuthActivity with its XML layout
        setContentView(R.layout.activity_auth)

        // Create the SharedPreferences file
        sharedPreferences = getSharedPreferences(
            "RecipeAppPreferences",
            MODE_PRIVATE
        )

        // Show the SplashFragment when the app starts
        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer, SplashFragment())
            .commit()
    }

    // This function opens the Login screen
    fun showLogin() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer, LoginFragment())
            .commit()
    }

    // This function opens the Register screen
    fun showRegister() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.authContainer, RegisterFragment())
            .commit()
    }
}