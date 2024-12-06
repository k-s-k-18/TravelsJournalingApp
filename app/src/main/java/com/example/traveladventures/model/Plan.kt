package com.example.traveladventures.model

import android.icu.text.DateFormat
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "PlanTravel")
data class Plan(
    @PrimaryKey
    val planId: UUID,
    val userId: UUID,
    val planName: String,
    val content: String,
    //val location: String
  //  val date: String, // Or use a Date object
    //this filde should be good
)
