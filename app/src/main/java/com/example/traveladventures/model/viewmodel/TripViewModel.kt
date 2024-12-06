package com.example.traveladventures.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveladventures.model.Note
import com.example.traveladventures.model.Plan
import com.example.traveladventures.model.TravelAdventuresRepository
import com.example.traveladventures.model.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class TripViewModel: ViewModel() {
    private val travelAdventuresRepository = TravelAdventuresRepository.get()

    //val trips = travelAdventuresRepository.getTrips(userId)


    suspend fun insertTrip(trip: Trip): Boolean {
        return try {
            travelAdventuresRepository.insertTrip(trip)
            true // Return success
        } catch (e: Exception) {
            e.printStackTrace() // Log the exception
            false // Return failure
        }
    }

    suspend fun getTrips(userId: UUID): Flow<List<Trip>> {
        return travelAdventuresRepository.getTrips(userId)
    }

    suspend fun getTrip(tripId: UUID): Trip {
        return travelAdventuresRepository.getTrip(tripId)
    }

    suspend fun deleteTrip(trip: Trip) {
        travelAdventuresRepository.deleteTrip(trip)
    }

    suspend fun updateTrip(trip: Trip) {
        travelAdventuresRepository.updateTrip(trip)
    }

    suspend fun getNotes(tripId: UUID): Flow<List<Note>> {
        return travelAdventuresRepository.getNotes(tripId)
    }

    suspend fun getTripByNameAndUserId(tripName: String, userId: UUID): Trip? {
        return travelAdventuresRepository.getTripByNameAndUserId(tripName, userId)
    }
}