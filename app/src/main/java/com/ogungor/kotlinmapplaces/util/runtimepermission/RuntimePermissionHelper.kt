package com.ogungor.kotlinmapplaces.util.runtimepermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker

class RuntimePermissionHelper(private val activity: AppCompatActivity) : RuntimePermissionListener {
    companion object {const val  REQUEST_ACCESS_FINE_LOCATION=1}


    override val hasAccessFineLocationPermission: Boolean
        get() = PermissionChecker.checkCallingOrSelfPermission(
            activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


    override fun getAccessFineLocationPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_ACCESS_FINE_LOCATION
        )
    }
}