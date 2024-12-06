package com.example.traveladventures.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {
    @GET("place/textsearch/json")
    suspend fun getLocation(
        @Query("query") query: String,
        @Query("key") key: String = "AIzaSyBhiVdTQHkEs1Yn6n5IEcBA3ptTKVX9R34"
    ): PlaceResponse
}
