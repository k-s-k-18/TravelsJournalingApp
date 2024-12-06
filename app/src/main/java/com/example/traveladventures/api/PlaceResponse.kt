package com.example.traveladventures.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceResponse(
    @Json(name="results") val placeItems: List<PlaceItem>
)
