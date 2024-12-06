package com.example.traveladventures.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.Query

class PlacesRepository {

    private val placesAPI: PlacesAPI

    init {


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        placesAPI = retrofit.create()
    }

    suspend fun getLocation(query: String):List<PlaceItem> = placesAPI.getLocation(query).placeItems

}