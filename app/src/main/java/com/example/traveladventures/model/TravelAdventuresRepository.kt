package com.example.traveladventures.model

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "trip-adventures-database"

class TravelAdventuresRepository private constructor(context: Context){

    // Trip database
    private val database: TravelAdventuresDatabase = Room.databaseBuilder(
        context.applicationContext,
        TravelAdventuresDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
    // TODO: fallbackToDestructiveMigration will wipe the database if the version changes. change this when everything is good to go

    // User account database
//    private val userAccountDatabase: UserAccountDatabase = Room.databaseBuilder(
//        context.applicationContext,
//        UserAccountDatabase::class.java,
//        "user-account-database"
//    ).fallbackToDestructiveMigration().build()

    fun getTrips(userId: UUID): Flow<List<Trip>> = database.travelAdventuresDao().getTrips(userId)

    suspend fun insertTrip(trip: Trip) {
        try {
            database.travelAdventuresDao().insertTrip(trip)
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Error inserting trip: ${e.message}")
        }
    }

    suspend fun getTrip(tripId: UUID): Trip = database.travelAdventuresDao().getTrip(tripId)

    suspend fun deleteTrip(trip: Trip) = database.travelAdventuresDao().deleteTrip(trip)

    suspend fun updateTrip(trip: Trip) = database.travelAdventuresDao().updateTrip(trip)

    suspend fun getNotes(tripId: UUID): Flow<List<Note>> = database.travelAdventuresDao().getNotes(tripId)

    suspend fun getTripByNameAndUserId(tripName: String, userId: UUID): Trip? {
        return database.travelAdventuresDao().getTripByNameAndUserId(tripName, userId)
    }


    // Note methods
    suspend fun getNote(noteId: UUID): Note = database.travelAdventuresDao().getNote(noteId)
    suspend fun insertNote(note: Note) = database.travelAdventuresDao().insertNote(note)
    suspend fun deleteNote(note: Note) = database.travelAdventuresDao().deleteNote(note)
    suspend fun updateNote(note: Note) = database.travelAdventuresDao().updateNote(note)
    suspend fun getNotes():List<Note> = database.travelAdventuresDao().getNotes()

    // Plan methods
    fun getPlans(userId: UUID): Flow<List<Plan>> = database.travelAdventuresDao().getPlans(userId)
    suspend fun insertPlan(plan: Plan) = database.travelAdventuresDao().insertPlan(plan)
    suspend fun getPlan(planId: UUID): Plan? = database.travelAdventuresDao().getPlan(planId)
    suspend fun deletePlan(plan: Plan) = database.travelAdventuresDao().deletePlan(plan)
    suspend fun updatePlan(plan: Plan) = database.travelAdventuresDao().updatePlan(plan)
    suspend fun getPlanByNameAndUserId(planName: String, userId: UUID): Plan? {
        return database.travelAdventuresDao().getPlanByNameAndUserId(planName, userId)
    }

    // User account methods
    suspend fun insert(userAccount: UserAccount)= database.travelAdventuresDao().insert(userAccount)
    suspend fun getUserAccount(email: String): UserAccount? = database.travelAdventuresDao().getUserAccount(email)


    companion object {
        private var INSTANCE: TravelAdventuresRepository?=null

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = TravelAdventuresRepository(context)
            }
        }

        fun get(): TravelAdventuresRepository {
            return INSTANCE?:throw IllegalStateException("Repository is not initialized")
        }
    }
}