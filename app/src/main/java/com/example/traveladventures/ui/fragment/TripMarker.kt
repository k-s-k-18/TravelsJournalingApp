package com.example.mapboxmapapp

import java.util.UUID
import com.mapbox.geojson.Point

data class IntentInformation(val noteId: UUID, val tripId:UUID, val userId:UUID)

data class TripMarker(val intentInformation: IntentInformation, val point: Point)