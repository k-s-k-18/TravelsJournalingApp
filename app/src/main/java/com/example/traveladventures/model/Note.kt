package com.example.traveladventures.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Note(@PrimaryKey val noteId: UUID, val noteTitle:String, val noteText:String, var noteLocationName: String, val mapId: UUID, val tripId: UUID, val userId: UUID, val noteLat:Double, val noteLong: Double)