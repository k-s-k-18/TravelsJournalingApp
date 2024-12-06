package com.example.traveladventures.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.ui.activity.LoginActivity
import com.example.traveladventures.model.UserAccount
import com.example.traveladventures.model.viewmodel.UserAccountViewModel
import com.example.traveladventures.databinding.FragmentSignupBinding
import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.viewmodel.UserAccountViewModelFactory
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val userAccountViewModel: UserAccountViewModel by viewModels() {
        UserAccountViewModelFactory(TravelAdventuresRepository.get())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            signUpButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()

                // Move checks to viewmodel
                viewLifecycleOwner.lifecycleScope.launch {

                    val resultMessage =
                        userAccountViewModel.registerUser(email, password, confirmPassword)
                    if (resultMessage != "Successfully created user") {
                        // If the user creation was not successful, display error
                        binding.signUpError.visibility = View.VISIBLE
                        binding.signUpError.text = resultMessage
                    } else {
                        // Return back to login page
                        Log.i(
                            "SignUpFragment",
                            "Successfully created user? returning back to login page"
                        )
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            cancelButton.setOnClickListener {
                // Returns to the login screen
                Log.i("SignUpFragment", "cancel button clicked")

                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}