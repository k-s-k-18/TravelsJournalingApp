package com.example.traveladventures.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mapboxmapapp.IntentInformation
import com.example.mapboxmapapp.TripMarker
import com.example.traveladventures.R
import com.example.traveladventures.databinding.FragmentMapBinding
import com.example.traveladventures.model.Note
import com.example.traveladventures.model.viewmodel.NoteViewModel
import com.example.traveladventures.ui.activity.MainActivity
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.not
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

@Suppress("DEPRECATION")
class MapFragment: Fragment() {

    private lateinit var binding: FragmentMapBinding
    private var _binding: FragmentMapBinding? = null

    private lateinit var mapView: MapView
    private lateinit var pointAnnotationManager: PointAnnotationManager

    private val noteViewModel: NoteViewModel by viewModels()
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    private val annotationIdMap = mutableMapOf<PointAnnotation, UUID>()
    private val annotationMap = mutableMapOf<PointAnnotation, IntentInformation>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = binding.mapView
        pointAnnotationManager = mapView.annotations.createPointAnnotationManager()

        // Check permissions and initialize map
        checkAndRequestPermissions()

        // Add marker icons and set the map style to Streets
        mapView.mapboxMap.loadStyle(Style.SATELLITE_STREETS) { style ->
            style.addImage("marker-icon-id", BitmapFactory.decodeResource(resources, R.drawable.red_marker))
        }
        /*
        // Add marker icons
        mapView.mapboxMap.getStyle { style ->
            style.addImage("marker-icon-id", BitmapFactory.decodeResource(resources, R.drawable.red_marker))
        }

         */

        // Load existing notes and add them to the map
        loadNotesAndDisplayMarkers()
    }

    private fun loadNotesAndDisplayMarkers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val notes = noteViewModel.getNotes()
            launch(Dispatchers.Main) {
                for (note in notes) {
                    val tripMarker = TripMarker(
                        intentInformation = IntentInformation(note.noteId, note.tripId, note.userId),
                        Point.fromLngLat(note.noteLong, note.noteLat)
                    )

                    val pointAnnotationOptions = PointAnnotationOptions()
                        .withPoint(tripMarker.point)
                        .withIconImage("marker-icon-id")

                    val annotation = pointAnnotationManager.create(pointAnnotationOptions)
                    annotationIdMap[annotation] = tripMarker.intentInformation.noteId
                    annotationMap[annotation] = tripMarker.intentInformation
                }
            }
        }

        // Set up marker click listener
        pointAnnotationManager.addClickListener(OnPointAnnotationClickListener { annotation ->
            val intentInformation = annotationMap[annotation]
            val activity = requireActivity() as? MainActivity
            if (intentInformation != null) {
                activity?.loadAddNote(
                    intentInformation.noteId,
                    intentInformation.tripId,
                    intentInformation.userId
                )
            }
            true
        })
    }

    private fun checkAndRequestPermissions() {
        if (requireContext().checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            initializeLocationComponent()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            initializeLocationComponent()
        } else {
            Toast.makeText(requireContext(), "Location permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeLocationComponent() {
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS is enabled, proceed to initialize location component
            mapView.location.updateSettings {
                enabled = true
                pulsingEnabled = true
            }
        } else {
            // GPS is not enabled, show a pop-up message
            showGpsErrorDialog()
        }
    }

    private fun showGpsErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("GPS Required")
            .setMessage("Some app functionality would not work without GPS turned on. Please enable GPS for real-time location updates.")
            .setCancelable(false)
            .setPositiveButton("Enable GPS") { _, _ ->
                // Open device location settings
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // Function to load the map style dynamically (Satellite Streets view)
    fun loadSatelliteStreetsStyle() {
        mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS) { style ->
            // Add the custom marker icon
            style.addImage("marker-icon-id", BitmapFactory.decodeResource(resources, R.drawable.red_marker))
        }
    }

    // Function to load the map style dynamically (Streets view)
    fun loadStreetsStyle() {
        mapView.mapboxMap.loadStyle(Style.MAPBOX_STREETS) { style ->
            // Add the custom marker icon
            style.addImage("marker-icon-id", BitmapFactory.decodeResource(resources, R.drawable.red_marker))
        }
    }

    // Function to unload the map and free resources (to save memory when not in use)
    fun unloadMapView() {
        mapView.onStop() // This will stop the map view, preventing it from rendering and consuming resources
        mapView.onDestroy() // This destroys the map view and releases resources
    }

    // Call this function when you want to switch between styles
    fun toggleMapStyle(isSatellite: Boolean) {
        if (isSatellite) {
            loadSatelliteStreetsStyle()  // Load satellite streets style
        } else {
            loadStreetsStyle()  // Load regular streets style
        }
    }

    // Call this function in the fragment's lifecycle methods to properly manage the view
    override fun onPause() {
        super.onPause()
        unloadMapView() // Unload the map when the fragment is not in the foreground
    }

    override fun onResume() {
        super.onResume()
        // Load the appropriate style when the fragment is resumed (based on user choice or default style)
        loadSatelliteStreetsStyle()  // Or loadStreetsStyle() depending on your condition
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadMapView()
        _binding = null
    }

}