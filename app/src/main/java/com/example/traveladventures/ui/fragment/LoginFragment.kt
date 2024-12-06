package com.example.traveladventures.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.ui.activity.SignUpActivity
import com.example.traveladventures.model.viewmodel.UserAccountViewModel
import com.example.traveladventures.databinding.FragmentLoginBinding
import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.viewmodel.UserAccountViewModelFactory
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    // private val userAccountViewModel: UserAccountViewModel by viewModels()
    private val userAccountViewModel: UserAccountViewModel by viewModels {
        UserAccountViewModelFactory(TravelAdventuresRepository.get())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = toHash(passwordEditText.text.toString())

                // Check if email and password are valid
                viewLifecycleOwner.lifecycleScope.launch {
                    val user = userAccountViewModel.getUserAccount(email)
                    // If user exists and it's the right password, proceed to Trips screen
                    if (user != null && user.password == password) {
                        Log.i("LoginFragment", "userId: $user")
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("userId", user.userId.toString())
                        startActivity(intent)
                    }
                    // If user exists but it's the wrong password, show error message
                    else if (user != null && user.password != password) {
                        binding.invalidLoginError.text = "Invalid password"
                        binding.invalidLoginError.visibility = View.VISIBLE
                    }
                    else if (user == null) {
                        // User doesn't exist. Show error message
                        binding.invalidLoginError.visibility = View.VISIBLE
                    }
                }
            }

            signUpButton.setOnClickListener {
                val intent = Intent(requireContext(), SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun toHash(password: String):String{
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}