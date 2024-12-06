package com.example.traveladventures.model

import android.app.Application

class TravelAdventuresApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TravelAdventuresRepository.initialize(this)
    }
}