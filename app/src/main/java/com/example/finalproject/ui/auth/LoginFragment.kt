package com.example.finalproject.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.MainActivity
import com.example.finalproject.R

class LoginFragment : Fragment() {

    // This function creates the Login Fragment screen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Connect the Fragment with the fragment_login.xml layout
        val view = inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )

        // Get the Email input field from the XML layout
        val emailEditText = view.findViewById<EditText>(
            R.id.emailEditText
        )

        // Get the Password input field from the XML layout
        val passwordEditText = view.findViewById<EditText>(
            R.id.passwordEditText
        )

        // Get the Login button from the XML layout
        val loginButton = view.findViewById<Button>(
            R.id.loginButton
        )

        // Get the Create Account button from the XML layout
        val registerButton = view.findViewById<Button>(
            R.id.registerButton
        )

        // When the Login button is clicked
        loginButton.setOnClickListener {

            // Read the email entered by the user
            val email = emailEditText.text.toString()

            // Read the password entered by the user
            val password = passwordEditText.text.toString()

            // Check that both fields are filled
            if (email.isEmpty() || password.isEmpty()) {

                // Show a message if any field is empty
                Toast.makeText(
                    requireContext(),
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Open the SharedPreferences file
                val sharedPreferences =
                    requireActivity().getSharedPreferences(
                        "RecipeAppPreferences",
                        Context.MODE_PRIVATE
                    )

                // Read the saved email
                val savedEmail = sharedPreferences.getString(
                    "email",
                    ""
                )

                // Read the saved password
                val savedPassword = sharedPreferences.getString(
                    "password",
                    ""
                )

                // Check if the entered data matches the saved data
                if (email == savedEmail && password == savedPassword) {

                    // Save login state
                    sharedPreferences.edit()
                        .putBoolean("isLoggedIn", true)
                        .apply()

                    // Show a successful login message
                    Toast.makeText(
                        requireContext(),
                        "Login successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Create an Intent to open MainActivity
                    val intent = Intent(
                        requireContext(),
                        MainActivity::class.java
                    )

                    // Start the MainActivity
                    startActivity(intent)

                    // Close the AuthActivity
                    requireActivity().finish()

                } else {

                    // Show an error message if the data is incorrect
                    Toast.makeText(
                        requireContext(),
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // When the Create Account button is clicked
        registerButton.setOnClickListener {

            // Open the Register Fragment
            (activity as AuthActivity).showRegister()
        }

        // Return the created Login Fragment view
        return view
    }
}