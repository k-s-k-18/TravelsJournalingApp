package com.example.traveladventures.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveladventures.model.Trip
import com.example.traveladventures.databinding.GridItemTripBinding
import com.example.traveladventures.ui.activity.MainActivity
import com.example.traveladventures.ui.fragment.TripGridFragment


class TripHolder(private val binding: GridItemTripBinding, private val context: TripGridFragment): RecyclerView.ViewHolder(binding.root){
    fun bind(trip: Trip){

        binding.tripName.text = trip.tripName

        binding.root.setOnClickListener{

            /*
            val intent = Intent( context.context, IndividualTripActivity::class.java)
            intent.putExtra("tripId",trip.tripId.toString())
            intent.putExtra("tripName",trip.tripName)
            context.context?.startActivity(intent)
             */
            Log.i("TripHolder", "tripId: ${trip.tripId}")
            val activity = context.activity as? MainActivity
            activity?.loadIndividualTrip(trip.tripId, trip.userId)
        }
    }
}

class TripGridListAdapter (private var trips: List<Trip>, val context: TripGridFragment): RecyclerView.Adapter<TripHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GridItemTripBinding.inflate(inflater, parent, false)
        return TripHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    override fun onBindViewHolder(holder: TripHolder, position: Int) {
        val trip = trips[position]

        holder.bind(trip)


    }

}