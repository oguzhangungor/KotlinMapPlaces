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
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { }


    }



    fun LocationPermission(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1,
                1f,
                locationListener
            )
            var lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            sendLocation(lastLocation)

        }


    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1,
                        1f,
                        locationListener

                    )
                    var lastLocation =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    sendLocation(lastLocation)
                } else {
                    Toast.makeText(this, "Please Permission Location Enable", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }



    fun sendLocation(lastLocation: Location?) {
        var userLastLocationLatitude = lastLocation!!.latitude
        var userLastLocationLongitude = lastLocation!!.longitude
        var intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("latitude", userLastLocationLatitude)
        intent.putExtra("longitude", userLastLocationLongitude)
        startActivity(intent)
        finish()
    }


}