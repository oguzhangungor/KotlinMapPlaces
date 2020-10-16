package com.ogungor.kotlinmapplaces.splash

import com.ogungor.kotlinmapplaces.R
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ogungor.kotlinmapplaces.util.extention.startLocationPermission
import com.ogungor.kotlinmapplaces.util.extention.startMapsActivity
import com.ogungor.kotlinmapplaces.util.runtimepermission.RuntimePermissionHelper


class SplashActivity : AppCompatActivity(), SplashActivityContract.View {

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
    private lateinit var presenter: SplashActivityContract.Presenter
    private lateinit var gifImageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val runtimePermissionListener = RuntimePermissionHelper(this)
        presenter = SplashActivityPresenter(runtimePermissionListener).apply {
            setView(this@SplashActivity)
            create()
        }


    }

    override fun initUi() {
        gifImageView= findViewById(R.id.imageView)
    }

    override fun starGifAnim() {
        Glide.with(this).load(R.drawable.loc).into(gifImageView)
    }



    override fun intentToMainActivity() {
        this.startMapsActivity()
    }

    override fun intentToLocationPermissionActivity() {
        this.startLocationPermission()
    }

    override fun finishActivity() {
        this.finish()
    }
}