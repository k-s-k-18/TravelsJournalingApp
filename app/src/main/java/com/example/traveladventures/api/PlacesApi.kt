package com.example.traveladventures.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {
    @GET("place/textsearch/json")
    suspend fun getLocation(
        @Query("query") query: String,
        @Query("key") key: String = "SECRET API KEY"
    ): PlaceResponse
}
