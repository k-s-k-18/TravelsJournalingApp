package com.example.traveladventures.ui.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.R
import com.example.traveladventures.model.Trip
import com.example.traveladventures.model.viewmodel.TripViewModel
import com.example.traveladventures.databinding.FragmentAddTripBinding
import com.example.traveladventures.model.Plan
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AddTripFragment: Fragment() {

    private lateinit var binding: FragmentAddTripBinding
    private val tripViewModel: TripViewModel by viewModels()
    private var tripID: UUID? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val userId = UUID.fromString(arguments?.getString("userId"))
        Log.i("AddTripFragment", " onStart userId: $userId")

        binding.addTripButton.setOnClickListener{
            val tripName = binding.tripNameEt.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val trip = Trip(UUID.randomUUID(), tripName, userId)
                tripViewModel.insertTrip(trip)

                (activity as? MainActivity)?.loadFragment(TripGridFragment(), userId)

            }
        }
    }
/*
    override fun onStart() {
        super.onStart()

        val userId = arguments?.getString("userId")?.let { UUID.fromString(it) }
            ?: throw IllegalArgumentException("User ID is required")

        binding.addTripButton.setOnClickListener {
            val tripName = binding.tripNameEt.text.toString()


            // Handle trip saving logic
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val existingTrip = tripViewModel.getTripByNameAndUserId(tripName, userId)
                    if (existingTrip != null) {
                        showDuplicateTripDialog(tripName, userId)
                    } else {
                        saveTrip(tripName, userId)
                    }
                } catch (e: Exception) {
                    Log.e("AddTripFragment", "Error while checking trip: ${e.message}", e)
                    showErrorDialog("Error", "Failed to save trip. Please try again.")
                }
            }
        }
    }

    // Function to show the duplicate trip dialog
    private fun showDuplicateTripDialog(tripName: String, userId: UUID) {
        AlertDialog.Builder(requireContext())
            .setTitle("Duplicate Trip Name")
            .setMessage("A trip with this name already exists. Are you sure you want to add a duplicate?")
            .setPositiveButton("Yes") { _, _ -> saveTrip(tripName, userId) }
            .setNegativeButton("No", null)
            .show()
    }

    // Function to save the trip
    private fun saveTrip(tripName: String, userId: UUID) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                if (tripID == null) {
                    // Create a new trip
                    val newTrip = Trip(UUID.randomUUID(), tripName, userId)
                    tripViewModel.insertTrip(newTrip)
                    Log.i("AddTripFragment", "New trip added: $tripName")
                } else {
                    // Update an existing trip
                    val updatedTrip = Trip(tripID!!, tripName, userId)
                    tripViewModel.updateTrip(updatedTrip)
                    Log.i("AddTripFragment", "Trip updated: $tripName")
                }

                // Navigate to TripGridFragment
                (activity as? MainActivity)?.loadFragment(TripGridFragment(), userId)
            } catch (e: Exception) {
                Log.e("AddTripFragment", "Error saving trip: ${e.message}", e)
                showErrorDialog("Error", "Failed to save trip. Please try again.")
            }
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }


    override fun onStart() {
        super.onStart()

        val userId = UUID.fromString(arguments?.getString("userId"))
        Log.i("AddTripFragment", "onStart userId: $userId")

        binding.addTripButton.setOnClickListener {
            val tripName = binding.tripNameEt.text.toString().trim()

            if (tripName.isEmpty()) {
                showErrorDialog("Error", "Trip name cannot be empty.")
                return@setOnClickListener
            }

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    // Check if a trip with the same name already exists
                    val existingTrip = tripViewModel.getTripByNameAndUserId(tripName, userId)
                    if (existingTrip != null) {
                        // Show an alert dialog if the trip already exists
                        withContext(Dispatchers.Main) {
                            showErrorDialog(
                                "Duplicate Trip",
                                "A trip with the name \"$tripName\" already exists. Please choose a different name."
                            )
                        }
                    } else {
                        // No duplicate found, proceed with saving the trip
                        val newTrip = Trip(UUID.randomUUID(), tripName, userId)
                        tripViewModel.insertTrip(newTrip)

                        withContext(Dispatchers.Main) {
                            Log.i("AddTripFragment", "New trip added: $tripName")
                            (activity as? MainActivity)?.loadFragment(TripGridFragment(), userId)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e("AddTripFragment", "Error checking or saving trip: ${e.message}", e)
                        showErrorDialog("Error", "Failed to save trip. Please try again.")
                    }
                }
            }
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

 */
}