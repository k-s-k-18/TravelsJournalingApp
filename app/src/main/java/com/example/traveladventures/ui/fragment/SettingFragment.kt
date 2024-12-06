package com.example.traveladventures.ui.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.traveladventures.databinding.FragmentSettingBinding
import com.example.traveladventures.ui.activity.MainActivity

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val preferenceKey = "dark_mode_enabled"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("AppPreferences", MODE_PRIVATE)

        // Load the current dark mode preference
        val isDarkModeEnabled = sharedPreferences.getBoolean(preferenceKey, true)
        Log.i("SettingFragment", "isDarkModeEnabled: $isDarkModeEnabled")
        /*
        binding.apply{
            darkModeSwitch.isChecked = isDarkModeEnabled
            darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
                handleDarkModeSwitch(isChecked)
            }
        }

         */
        binding.darkModeSwitch.isChecked = isDarkModeEnabled

        // Setup the toggle listener

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the preference and apply the selected theme mode
            handleDarkModeSwitch(isChecked)
        }
    }

    private fun handleDarkModeSwitch(isChecked: Boolean) {
        // Save the dark mode preference
        sharedPreferences.edit().putBoolean(preferenceKey, isChecked).apply()

        // Apply the selected theme mode
        val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}