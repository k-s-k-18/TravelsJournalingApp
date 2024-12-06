package com.example.traveladventures.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [Trip::class, UserAccount::class, Plan::class, Note::class], version = 9, exportSchema = false)
abstract class TravelAdventuresDatabase : RoomDatabase() {
    abstract fun travelAdventuresDao(): TravelAdventuresDao

    companion object {
        @Volatile
        private var INSTANCE: TravelAdventuresDatabase? = null

        fun getDatabase(context: Context): TravelAdventuresDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelAdventuresDatabase::class.java, "travel_adventures_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}