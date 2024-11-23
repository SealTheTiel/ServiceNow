package com.gold.servicenow

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gold.servicenow.carthistory.CartHistoryActivity
import org.osmdroid.api.IGeoPoint
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.util.Locale

class SetLocationActivity : ComponentActivity() {

    private lateinit var proceed: Button
    private lateinit var locationAddLocation: RadioButton
    private lateinit var locationAutoLocation: RadioButton
    private lateinit var locationMap: MapView
    private lateinit var locationAddInput: EditText
    private lateinit var locationAutoText: TextView
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private var currentSelection: String = "ADD" // Default selection is Add Location
    private var myLocationOverlay: MyLocationNewOverlay? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location)
        Configuration.getInstance().userAgentValue = packageName

        // Request location permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        // Adjust insets for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        proceed = findViewById(R.id.paymentProceedButton)
        locationAutoLocation = findViewById(R.id.locationAutoButton)
        locationAddLocation = findViewById(R.id.locationAddButton)
        locationMap = findViewById(R.id.locationMap)
        locationAddInput = findViewById(R.id.locationAddInput)
        locationAutoText = findViewById(R.id.locationAutoText)

        // Set default to Add Location
        initializeAddLocation()

        // Proceed to payment method
        proceed.setOnClickListener {
            val intent = Intent(this, CartHistoryActivity::class.java)

            // Get the selected location based on the current mode
            val location = if (currentSelection == "AUTO") {
                locationAutoText.text.toString().takeIf { it.isNotBlank() } ?: "Unable to detect location."
            } else {
                locationAddInput.text.toString().takeIf { it.isNotBlank() } ?: "No location entered."
            }

            // Add the location as an extra
            intent.putExtra("selectedLocation", location)

            // Start CartHistoryActivity
            startActivity(intent)
        }

        // Auto Location Button logic
        locationAutoLocation.setOnClickListener {
            if (currentSelection != "AUTO") {
                currentSelection = "AUTO"
                initializeAutoLocation()
            }
        }

        // Add Location Button logic
        locationAddLocation.setOnClickListener {
            if (currentSelection != "ADD") {
                currentSelection = "ADD"
                initializeAddLocation()
            }
        }
    }

    // Initialize Add Location logic
    private fun initializeAddLocation() {
        // Show the map and configure it
        locationMap.visibility = android.view.View.VISIBLE
        locationMap.setMultiTouchControls(true)
        locationMap.controller.setZoom(15.0)
        locationMap.controller.setCenter(GeoPoint(14.5648, 120.9932))

        // Add double-tap listener for choosing a location
        setupDoubleTapListener()

        // Add markers with `ItemizedOverlayWithFocus`
        setupDefaultMarker()

        // Mark Add Location button as selected
        locationAddLocation.isChecked = true

        // Mark Auto Location button as unselected
        locationAutoLocation.isChecked = false
    }

    // Initialize Auto Location logic
    private fun initializeAutoLocation() {
        // Hide the map for auto location
        locationMap.visibility = android.view.View.GONE

        // Detect and display current location
        enableMyLocation(true)


        // Mark Auto Location button as selected
        locationAutoLocation.isChecked = true

        // Mark Add Location button as unselected
        locationAddLocation.isChecked = false
    }

    // Detect device location and convert to address
    private fun enableMyLocation(autoDetect: Boolean = false) {
        if (myLocationOverlay == null) {
            myLocationOverlay = MyLocationNewOverlay(locationMap)
            myLocationOverlay!!.enableMyLocation()
            myLocationOverlay!!.enableFollowLocation()
            locationMap.overlays.add(myLocationOverlay)
        }
        println("ok")

        if (autoDetect) {
            locationAutoText.setText("Loading...")

            // Retry location after a short delay to allow MyLocationNewOverlay to initialize
            locationMap.postDelayed({
                val location = myLocationOverlay!!.myLocation
                if (location != null) {
                    println("ok1")
                    val lat = location.latitude
                    val lon = location.longitude

                    // Convert lat and lon to a human-readable address
                    val geocoder = Geocoder(this, Locale.getDefault())
                    try {
                        val addresses = geocoder.getFromLocation(lat, lon, 1)
                        if (addresses != null && addresses.isNotEmpty()) {
                            val address = addresses[0].getAddressLine(0)
                            locationAutoText.setText(address)
                            Log.d("AutoLocation", "Detected location: $address")
                        } else {
                            locationAutoText.setText("Lat: $lat, Lon: $lon")
                            Log.d("AutoLocation", "No address found for location")
                        }
                    } catch (e: Exception) {
                        locationAddInput.setText("Lat: $lat, Lon: $lon")
                        Log.e("AutoLocation", "Error converting location to address: ${e.message}")
                    }
                } else {
                    Log.d("AutoLocation", "Location not detected, retrying...")
                    // Retry enabling the location after a short delay if it's still null
                    enableMyLocation(true)
                }
            }, 2000) // Delay for 2 seconds
        }
    }
    private fun setupDoubleTapListener() {
        // Define a MapEventsReceiver to handle single taps
        val mapEventsReceiver = object : org.osmdroid.events.MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                val lat = p.latitude
                val lon = p.longitude

                // Convert latitude and longitude to address
                val geocoder = Geocoder(this@SetLocationActivity, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(lat, lon, 1)
                    val address = if (addresses != null && addresses.isNotEmpty()) {
                        addresses[0].getAddressLine(0)
                    } else {
                        "Lat: $lat, Lon: $lon"
                    }
                    locationAddInput.setText(address)

                    // Add marker at clicked location
                    addMarker(p, address)
                } catch (e: Exception) {
                    locationAddInput.setText("Lat: $lat, Lon: $lon")
                    Log.e("MapClick", "Error converting location: ${e.message}")
                }
                return true
            }

            override fun longPressHelper(p: GeoPoint): Boolean {
                // Handle long press if needed
                return false
            }
        }

        // Add a MapEventsOverlay to handle the events
        val mapEventsOverlay = org.osmdroid.views.overlay.MapEventsOverlay(mapEventsReceiver)
        locationMap.overlays.add(mapEventsOverlay)
    }

    private fun addMarker(location: GeoPoint, title: String) {
        // Remove all existing markers
        locationMap.overlays.removeIf { it is Marker }

        // Add the new marker
        val marker = Marker(locationMap)
        marker.position = location
        marker.title = title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        locationMap.overlays.add(marker)

        // Refresh the map view to display the updated marker
        locationMap.invalidate()
    }



    //    // Setup overlay items using `ItemizedOverlayWithFocus`
    private fun setupDefaultMarker() {
        // Set the default location to DLSU
        val dlsuLocation = GeoPoint(14.5648, 120.9932)
        val marker = Marker(locationMap)
        marker.position = dlsuLocation
        marker.title = "De La Salle University"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

        // Add the marker to the map
        locationMap.overlays.add(marker)

        // Center the map on the default location
        locationMap.controller.setZoom(15.0)
        locationMap.controller.setCenter(dlsuLocation)
    }}

