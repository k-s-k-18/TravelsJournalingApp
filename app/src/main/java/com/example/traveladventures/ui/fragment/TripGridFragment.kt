package com.example.traveladventures.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.traveladventures.model.viewmodel.TripViewModel
import com.example.traveladventures.databinding.FragmentTripGridBinding
import com.example.traveladventures.ui.TripGridListAdapter
import com.example.traveladventures.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

const val TAG="TripGridFragment"

class TripGridFragment: Fragment() {

    private lateinit var binding: FragmentTripGridBinding
    private val tripViewModel: TripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater:LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        binding = FragmentTripGridBinding.inflate(layoutInflater, container, false)

        binding.tripsRecycleView.layoutManager = GridLayoutManager(context, 2)

        //userId = arguments?.getString("userId")?.let { UUID.fromString(it) }
        //Log.i("TripGridFragment", "tripgridfrag, userId: $userId")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = UUID.fromString(arguments?.getString("userId"))
        Log.i("TripGridFragment", "onviewcreated userId: $userId")

                CoroutineScope(Dispatchers.IO).launch {
                    //val trips: Flow<List<Trip>> = tripViewModel.getTrips()
                    // Print out the list of trips
                    //Log.i(TAG, "Trips: ${trips.joinToString(separator = "\n")}")


                    /*val trips: List<Trip> = tripViewModel.getTrips()

                    lifecycleScope.launch(Dispatchers.Main) {
                        val adapter = TripGridListAdapter(trips, this@TripGridFragment)
                        binding.tripsRecycleView.adapter = adapter
                    }*/

                    // Load trips from the ViewModel
                    if (userId != null) {
                        tripViewModel.getTrips(userId!!).collect{ trips ->
                            lifecycleScope.launch(Dispatchers.Main) {
                                val adapter = TripGridListAdapter(trips, this@TripGridFragment)
                                binding.tripsRecycleView.adapter = adapter
                            }
                        }
                    }
                }

                binding.addButton.setOnClickListener{
                    if (userId != null) {
                        (activity as? MainActivity)?.loadFragment(AddTripFragment(), userId)
                    }
                   /*val intent = Intent(context, AddTripActivity::class.java)
                    startActivity(intent)
                    val addTripDialog = AddTripDialogFragment()
                    addTripDialog.show(parentFragmentManager, "AddTripDialog")*/
                }
    }

    companion object {
        fun newInstance(userId: UUID): TripGridFragment {
            Bundle().putString("userId", userId.toString())
            TripGridFragment().arguments = Bundle()
            return TripGridFragment()
        }
    }
}