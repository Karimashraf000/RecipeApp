package com.example.finalproject.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.R

class RegisterFragment : Fragment() {

    // This function creates the Register Fragment screen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Connect the Fragment with the fragment_register.xml layout
        val view = inflater.inflate(
            R.layout.fragment_register,
            container,
            false
        )

        // Get the Register button from the XML layout
        val registerButton = view.findViewById<Button>(
            R.id.registerButton
        )

        // Get the Login button from the XML layout
        val loginButton = view.findViewById<Button>(
            R.id.loginButton
        )

        // Get the Name input field
        val nameEditText = view.findViewById<EditText>(
            R.id.nameEditText
        )

        // Get the Email input field
        val emailEditText = view.findViewById<EditText>(
            R.id.emailEditText
        )

        // Get the Password input field
        val passwordEditText = view.findViewById<EditText>(
            R.id.passwordEditText
        )

        // When the Register button is clicked
        registerButton.setOnClickListener {

            // Read the name entered by the user
            val name = nameEditText.text.toString()

            // Read the email entered by the user
            val email = emailEditText.text.toString()

            // Read the password entered by the user
            val password = passwordEditText.text.toString()

            // Check that all fields are filled
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

                // Show a message if any field is empty
                Toast.makeText(
                    requireContext(),
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Open the SharedPreferences file created in AuthActivity
                val sharedPreferences =
                    requireActivity().getSharedPreferences(
                        "RecipeAppPreferences",
                        Context.MODE_PRIVATE
                    )

                // Save the user's name, email and password
                sharedPreferences.edit()
                    .putString("name", name)
                    .putString("email", email)
                    .putString("password", password)
                    .apply()

                // Show a success message
                Toast.makeText(
                    requireContext(),
                    "Account created successfully",
                    Toast.LENGTH_SHORT
                ).show()

                // Go to the Login screen after registration
                (activity as AuthActivity).showLogin()
            }
        }

        // When the Login button is clicked
        loginButton.setOnClickListener {

            // Open the Login Fragment
            (activity as AuthActivity).showLogin()
        }

        // Return the created Register Fragment view
        return view
    }
}