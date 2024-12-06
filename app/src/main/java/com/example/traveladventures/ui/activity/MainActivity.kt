package com.example.traveladventures.ui.activity


import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.traveladventures.R
import com.example.traveladventures.databinding.ActivityMainBinding
import com.example.traveladventures.ui.fragment.AddNoteFragment
import com.example.traveladventures.ui.fragment.AddPlanFragment
import com.example.traveladventures.ui.fragment.IndividualTripFragment
import com.example.traveladventures.ui.fragment.MapFragment
import com.example.traveladventures.ui.fragment.PlanFragment
import com.example.traveladventures.ui.fragment.ProfileFragment
import com.example.traveladventures.ui.fragment.SettingFragment
import com.example.traveladventures.ui.fragment.TripGridFragment
import com.example.traveladventures.ui.fragment.UpdateTripFragment
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        if (savedInstanceState == null) {
            userId = intent.getStringExtra("userId")
            Log.i("MainActivity", "if statement: userId from Intent: $userId")

            // Load the default Trip page fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TripGridFragment().apply {
                    arguments = Bundle().apply {
                        putString("userId", userId)
                    }
                }).commit()
        } else {
            // Retrieve the userId from savedInstanceState after rotation
            userId = savedInstanceState.getString("userId")
            Log.i("MainActivity", "else statement: userId from savedInstanceState: $userId")
        }

        // Navigation bar button listeners
        // Trips
        binding.navTrips.setOnClickListener {
            loadFragment(TripGridFragment(), UUID.fromString(userId))
        }
        // Plans
        binding.navPlan.setOnClickListener {
            Log.i("MainActivity", "Plans button clicked")
            loadFragment(PlanFragment(), UUID.fromString(userId))
        }
        // Maps
        binding.navMap.setOnClickListener {
            loadFragment(MapFragment(), UUID.fromString(userId))
        }
        // Profile
        binding.navProfile.setOnClickListener {
            loadFragment(ProfileFragment(), UUID.fromString(userId))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("userId", userId)
        Log.i("MainActivity", "onSaveInstanceState() called")
    }

    fun loadFragment(fragment: Fragment, userId: UUID?) {
        Log.i("MainActivity", "fragment: $fragment")
        val arguments = Bundle().apply {
            putString("userId", userId.toString())
        }
        fragment.arguments = arguments

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()

        Log.i("MainActivity", "loadFragment() called")
    }

    fun loadAddPlan(planId: UUID? = null, userId: UUID?) {
        Log.i("MainActivity", "loadAddPlan() called")

        val fragment = AddPlanFragment().apply {
            arguments = Bundle().apply {
                planId?.let { putString("planId", planId.toString()) }
                userId?.let { putString("userId", userId.toString()) }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun loadIndividualTrip(tripId: UUID? = null, userId: UUID?) {
        Log.i("MainActivity", "loadIndividualTrip() called")

        val fragment = IndividualTripFragment().apply {
            arguments = Bundle().apply {
                tripId?.let { putString("tripId", tripId.toString()) }
                userId?.let { putString("userId", userId.toString()) }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun loadUpdateTrip(tripId: UUID? = null, userId: UUID?) {
        Log.i("MainActivity", "loadUpdatePlan() called")

        val fragment = UpdateTripFragment().apply {
            arguments = Bundle().apply {
                tripId?.let { putString("tripId", tripId.toString()) }
                userId?.let { putString("userId", userId.toString()) }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun loadAddNote(noteId: UUID? = null, tripId: UUID? = null, userId: UUID?) {
        Log.i("MainActivity", "loadAddNote() called")
        Log.i("MainActivity", "tripId: $tripId")
        Log.i("MainActivity", "noteId: $noteId")

        val fragment = AddNoteFragment().apply {
            arguments = Bundle().apply {
                noteId?.let { putString("noteId", noteId.toString()) }
                tripId?.let { putString("tripId", tripId.toString()) }
                userId?.let { putString("userId", userId.toString()) }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}