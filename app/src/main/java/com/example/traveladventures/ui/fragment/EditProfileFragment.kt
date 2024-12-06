package com.example.traveladventures.ui.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.R
import com.example.traveladventures.model.Trip
import com.example.traveladventures.model.viewmodel.TripViewModel
import com.example.traveladventures.databinding.FragmentEditProfileBinding
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class EditProfileFragment: Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private val tripViewModel: TripViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the saved profile name and populate the EditText
        val sharedPreferences = requireContext().getSharedPreferences("UserProfilePrefs", Context.MODE_PRIVATE)
        val savedProfileName = sharedPreferences.getString("PROFILE_NAME", "") // Default to empty string if not saved
        binding.editProfileName.setText(savedProfileName)

        // Save the profile name when the saveButton is clicked
        binding.saveButton.setOnClickListener {
            val profileName = binding.editProfileName.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("PROFILE_NAME", profileName)
            editor.apply()
            Log.i("ProfileFragment", "Profile name saved: $profileName")
            Toast.makeText(requireContext(), "Profile name saved!", Toast.LENGTH_SHORT).show()
            (activity as? MainActivity)?.loadFragment(ProfileFragment(), null)
        }
    }
}