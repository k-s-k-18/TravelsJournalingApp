package com.example.traveladventures.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveladventures.model.viewmodel.TripViewModel
import com.example.traveladventures.databinding.FragmentIndividualTripBinding
import com.example.traveladventures.ui.NoteListAdapter
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class IndividualTripFragment: Fragment() {
    private lateinit var binding: FragmentIndividualTripBinding

    private val tripViewModel: TripViewModel by viewModels()
    private var userId: UUID? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndividualTripBinding.inflate(inflater, container, false)

        // Display list of notes for the trip
        binding.tripNotesRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tripId = arguments?.getString("tripId")?.let { UUID.fromString(it) }
        val tripName = arguments?.getString("tripName")

        userId = UUID.fromString(arguments?.getString("userId"))

        Log.i("IndividualTripFragment", "tripId: $tripId")
        //binding.tripNameTv.text = tripName
        CoroutineScope(Dispatchers.IO).launch {
            val trip = tripViewModel.getTrip(tripId!!)
            binding.tripNameTv.text = trip.tripName

            // Load notes for the trip
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    tripViewModel.getNotes(tripId).collect { notes ->
                        binding.tripNotesRecyclerView.adapter = NoteListAdapter(notes, this@IndividualTripFragment)
                    }
                }
            }
        }

        binding.deleteButton.setOnClickListener{
            if (tripId != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    val trip = tripViewModel.getTrip(tripId)
                    tripViewModel.deleteTrip(trip)

                    lifecycleScope.launch(Dispatchers.Main) {
                        //parentFragmentManager.popBackStack()
                        (activity as? MainActivity)?.loadFragment(TripGridFragment(), userId)
                    }
                }
            }
        }

        binding.editButton.setOnClickListener{
            /*
            val intent = Intent(context, UpdateTripFragment::class.java)
            intent.putExtra("tripId",tripId.toString())
            startActivity(intent)
             */

            /*
            val editFragment = UpdateTripFragment.newInstance(userId)
            val bundle = Bundle().apply {
                putString("tripId", tripId.toString())
            }
            editFragment.arguments = bundle*/

            (activity as? MainActivity)?.loadUpdateTrip(tripId, userId)

            /*
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .addToBackStack(null)
                .commit()

             */
        }

        binding.addNoteButton.setOnClickListener{
            Log.i("IndividualTripFragment", "addNoteButton clicked, going to MainActivity.loadAddNote()")
            Log.i("IndividualTripFragment", "tripId: $tripId")
            val noteId = null // null since this is a new note
            (activity as? MainActivity)?.loadAddNote(noteId, tripId, userId)
        }
    }
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = FragmentIndividualTripBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val tripId = UUID.fromString(intent.getStringExtra("tripId"))
        val tripName = intent.getStringExtra("tripName")

        binding.tripNameTv.setText(tripName).toString()

        binding.deleteButton.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                val trip = tripViewModel.getTrip(tripId)
                tripViewModel.deleteTrip(trip)

                lifecycleScope.launch(Dispatchers.Main) {
                    val intent = Intent(this@IndividualTripFragment, TripActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        binding.editButton.setOnClickListener{
            val intent = Intent(this, UpdateTripFragment::class.java)
            intent.putExtra("tripId",tripId.toString())
            startActivity(intent)
        }



    }

 */
    companion object {
        fun newInstance(userId: UUID): IndividualTripFragment {
            val fragment = IndividualTripFragment()
            Bundle().putString("userId", userId.toString())
            fragment.arguments = Bundle()
            return fragment
    }
}
}