package com.gold.servicenow

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
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
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
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
            val intent = Intent(this, PaymentMethod::class.java)
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
        //setupOverlayItems()

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






    // Add double-tap listener to choose location
    private fun setupDoubleTapListener() {
        locationMap.setOnTouchListener { _, event ->
            if (event.pointerCount == 1 && event.actionMasked == android.view.MotionEvent.ACTION_UP) {
                val tappedLocation = locationMap.projection.fromPixels(event.x.toInt(), event.y.toInt())
                val lat = tappedLocation.latitude
                val lon = tappedLocation.longitude

                // Convert latitude and longitude to address
                val geocoder = Geocoder(this, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(lat, lon, 1)
                    if (addresses != null) {
                        if (addresses.isNotEmpty()) {
                            val address = addresses?.get(0)?.getAddressLine(0)
                            locationAddInput.setText(address)
                        } else {
                            locationAddInput.setText("Lat: $lat, Lon: $lon")
                        }
                    }
                } catch (e: Exception) {
                    locationAddInput.setText("Lat: $lat, Lon: $lon")
                    Log.e("MapClick", "Error converting location: ${e.message}")
                }
            }
            false
        }
    }

//    // Setup overlay items using `ItemizedOverlayWithFocus`
    private fun setupOverlayItems() {
        // Create a list of overlay items
        val items = ArrayList<OverlayItem>()
        items.add(OverlayItem("", "", GeoPoint(14.5648, 120.9932)))
        // Create the overlay
        val overlay = ItemizedOverlayWithFocus(
            items,
            object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                    val lat = item.point.latitude
                    val lon = item.point.longitude
                    locationAddInput.setText("Lat: $lat, Lon: $lon")
                    return true
                }

                override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                    return false
                }
            },
            this
        )
        overlay.setFocusItemsOnTap(true)
        locationMap.overlays.add(overlay)
    }
}
