package com.example.traveladventures.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Places(
    @Json(name="results") val placeItems: List<PlaceItem>
)