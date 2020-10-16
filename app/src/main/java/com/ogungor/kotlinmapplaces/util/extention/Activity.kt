package com.ogungor.kotlinmapplaces.util.extention

import android.app.Activity
import android.content.Intent
import com.ogungor.kotlinmapplaces.LocationPermissionActivity
import com.ogungor.kotlinmapplaces.MapsActivity

fun Activity.startMapsActivity() {
    this.startActivity(
        Intent(
            this, MapsActivity::class.java
        )
    )

}

fun Activity.startLocationPermission(){
    this.startActivity(Intent(this, LocationPermissionActivity::class.java))
}