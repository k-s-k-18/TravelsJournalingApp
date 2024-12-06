package com.example.traveladventures.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.model.Trip
import com.example.traveladventures.model.viewmodel.TripViewModel
import com.example.traveladventures.databinding.FragmentUpdateTripBinding
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class UpdateTripFragment : Fragment() {

    private lateinit var binding: FragmentUpdateTripBinding
    private val tripViewModel: TripViewModel by viewModels()
    //private var userId: UUID? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val tripId = UUID.fromString(intent.getStringExtra("tripId"))
        val tripId = UUID.fromString(arguments?.getString("tripId"))
        val userId = UUID.fromString(arguments?.getString("userId"))
        Log.i("UpdateTripFragment", "userId: $userId")

        binding.editTripButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {

                val trip = Trip(tripId, binding.tripNameEt.text.toString(), userId)

                tripViewModel.updateTrip(trip)

                lifecycleScope.launch(Dispatchers.Main) {
                    (activity as? MainActivity)?.loadFragment(TripGridFragment(), userId)
                }
            }
        }
    }
}