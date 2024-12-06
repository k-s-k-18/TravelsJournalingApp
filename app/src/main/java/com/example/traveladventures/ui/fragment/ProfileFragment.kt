package com.example.traveladventures.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveladventures.databinding.FragmentProfileBinding
import com.example.traveladventures.ui.activity.LoginActivity
import com.example.traveladventures.ui.activity.MainActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    //private val userAccountViewModel: UserAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editProfileButton.setOnClickListener {
                Log.i("ProfileFragment", "Edit profile button clicked")
                (activity as? MainActivity)?.loadFragment(EditProfileFragment(), null)
            }

            settingsButton.setOnClickListener {
                Log.i("ProfileFragment", "Settings button clicked")
                (activity as? MainActivity)?.loadFragment(SettingFragment(), null)
            }

            changePasswordButton.setOnClickListener {
                Log.i("ProfileFragment", "Change password button clicked")
                (activity as? MainActivity)?.loadFragment(ChangePasswordFragment(), null)
            }

            logoutButton.setOnClickListener {
                Log.i("ProfileFragment", "Settings button clicked")
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}


