package com.example.traveladventures.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TravelAdventuresDao {

    // Trip
    @Query("SELECT * FROM trip WHERE userId=(:userId)")
    fun getTrips(userId: UUID): Flow<List<Trip>>

    @Insert
    fun insertTrip(trip: Trip)

    @Delete
    fun deleteTrip(trip: Trip)

    @Query("SELECT * FROM trip where tripId=(:tripId)")
    fun getTrip(tripId: UUID): Trip

    @Update
    fun updateTrip(trip: Trip)

    @Query("SELECT * FROM note WHERE tripId=(:tripId)")
    fun getNotes(tripId: UUID): Flow<List<Note>>

    @Query("SELECT * FROM trip WHERE tripName = (:tripName) AND userId = (:userId)")
    suspend fun getTripByNameAndUserId(tripName: String, userId: UUID): Trip?

    // User Account
    @Insert
    suspend fun insert(userAccount: UserAccount)

    @Query("SELECT * FROM useraccount WHERE email = :email")
    suspend fun getUserAccount(email: String): UserAccount?

    // Note
    @Query("SELECT * FROM Note WHERE noteId=(:noteId)")
    suspend fun getNote(noteId: UUID): Note

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * From Note")
    suspend fun getNotes(): List<Note>

    // Plan
    @Query("SELECT * FROM PlanTravel WHERE userId=(:userId)")
    fun getPlans(userId: UUID): Flow<List<Plan>>

    @Insert
    suspend fun insertPlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)

    @Query("SELECT * FROM PlanTravel where planId=(:planId)")
    suspend fun getPlan(planId: UUID): Plan

    @Update
    suspend fun updatePlan(plan: Plan)

    @Query("SELECT * FROM PlanTravel WHERE planName = (:planName) AND userId = (:userId)")
    suspend fun getPlanByNameAndUserId(planName: String, userId: UUID): Plan?


}