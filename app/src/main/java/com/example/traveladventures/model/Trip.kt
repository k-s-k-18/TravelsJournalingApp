package com.example.traveladventures.model

import android.icu.text.DateFormat
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Trip(@PrimaryKey val tripId: UUID, val tripName: String, val userId: UUID)

