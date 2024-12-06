package com.example.traveladventures.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.traveladventures.databinding.FragmentAddTripDialogBinding
import com.example.traveladventures.model.Trip
import com.example.traveladventures.model.viewmodel.TripViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class AddTripDialogFragment : DialogFragment() {
/*
    private lateinit var binding: FragmentAddTripDialogBinding
    private val tripViewModel: TripViewModel by viewModels()
    private val userId: String = arguments?.getString("userId").toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTripDialogBinding.inflate(inflater, container, false)

        binding.addTripButton.setOnClickListener{
            val tripName = binding.tripNameEt.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val trip = Trip(UUID.randomUUID(), tripName, UUID.fromString(userId))
                tripViewModel.insertTrip(trip)
            }
            dismiss()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance(userId: UUID): TripGridFragment {
            Bundle().putString("userId", userId.toString())
            TripGridFragment().arguments = Bundle()
            return TripGridFragment()
        }
    }

 */
}
