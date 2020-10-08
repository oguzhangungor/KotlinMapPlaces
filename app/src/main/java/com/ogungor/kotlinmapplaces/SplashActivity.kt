package com.ogungor.kotlinmapplaces

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide


class SplashActivity : AppCompatActivity() {

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { }


        val imageView: ImageView = findViewById(R.id.imageView)
        Glide.with(this).load(R.drawable.loc).into(imageView)

        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(7 * 1000.toLong())

                    // After 5 seconds redirect to another intent
                    if (ContextCompat.checkSelfPermission(
                            this@SplashActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        println("izin yok")
                        var intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        println("izin var")
                        var lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        sendLocation(lastLocation)
                    }




                    //Remove activity
                    finish()
                } catch (e: Exception) {
                }
            }
        }
        background.start()

    }

    fun sendLocation(lastLocation: Location?) {
        var userLastLocationLatitude = lastLocation!!.latitude
        var userLastLocationLongitude = lastLocation!!.longitude
        var intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("latitude", userLastLocationLatitude)
        intent.putExtra("longitude", userLastLocationLongitude)
        startActivity(intent)
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
                    var lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    sendLocation(lastLocation)
                } else {
                    Toast.makeText(this, "Please Permission Location Enable", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}