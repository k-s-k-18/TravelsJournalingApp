package com.example.traveladventures.api

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class PlaceItem(
    @Json(name = "formatted_address") val formattedAddress: String,
    val name: String,
    val geometry: Geometry
)

@JsonClass(generateAdapter = true)
data class Geometry(
    val location: Location
)

@JsonClass(generateAdapter = true)
data class Location(
    val lat: Double,
    @Json(name = "lng") val long: Double
)
