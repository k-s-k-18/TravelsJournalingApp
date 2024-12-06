package com.example.traveladventures.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "useraccount")
data class UserAccount(
    @PrimaryKey val userId: UUID,
    val email: String,
    val password: String,
)
