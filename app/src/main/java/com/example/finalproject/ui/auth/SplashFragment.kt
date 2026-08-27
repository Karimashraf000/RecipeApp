package com.example.finalproject.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.MainActivity
import com.example.finalproject.R

class SplashFragment : Fragment() {

    // This function creates the Splash Fragment screen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Connect the Fragment with the fragment_splash.xml layout
        return inflater.inflate(
            R.layout.fragment_splash,
            container,
            false
        )
    }

    // This function runs after the Splash screen is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Wait for 2 seconds before checking the login status
        view.postDelayed({

            // Open the SharedPreferences file
            val sharedPreferences =
                requireActivity().getSharedPreferences(
                    "RecipeAppPreferences",
                    Context.MODE_PRIVATE
                )

            // Read the user's login status
            val isLoggedIn = sharedPreferences.getBoolean(
                "isLoggedIn",
                false
            )

            // Check if the user has logged in before
            if (isLoggedIn) {

                // If the user is already logged in,
                // open the MainActivity directly
                val intent = Intent(
                    requireContext(),
                    MainActivity::class.java
                )

                startActivity(intent)

                // Close AuthActivity so the user cannot go back
                // to the Login or Register screens
                requireActivity().finish()

            } else {

                // If the user has not logged in before,
                // open the Login screen
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.authContainer,
                        LoginFragment()
                    )
                    .commit()
            }

        }, 2000)
    }
}