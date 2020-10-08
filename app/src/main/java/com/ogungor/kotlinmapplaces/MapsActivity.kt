package com.ogungor.kotlinmapplaces

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
    var userLastLocation: LatLng?= null
    var userLastLocationLatitude: Double=0.0
    var userLastLocationLongitude:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        var intent=intent
         userLastLocationLatitude=intent.getDoubleExtra("latitude",1.0)
         userLastLocationLongitude=intent.getDoubleExtra("longitude",1.0)
         userLastLocation=LatLng(userLastLocationLatitude,userLastLocationLongitude)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        println("user: "+ userLastLocation)

        if (userLastLocation != null) {
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(userLastLocation!!).title("Last Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLocation, 20f))
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
               //Mevcut bulunan konum
                /*if (p0 != null) {
                    mMap.clear()
                    var userLocation = LatLng(p0.latitude, p0.longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Location user"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 20f))
                }

                 */



            }

        }



    }





}