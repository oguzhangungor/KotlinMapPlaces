package com.ogungor.kotlinmapplaces.util.runtimepermission

interface RuntimePermissionListener {

    val hasAccessFineLocationPermission: Boolean

    fun getAccessFineLocationPermission()

}