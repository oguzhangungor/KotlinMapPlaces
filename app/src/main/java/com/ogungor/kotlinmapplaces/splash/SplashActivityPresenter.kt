package com.ogungor.kotlinmapplaces.splash

import android.os.Handler

import com.ogungor.kotlinmapplaces.util.runtimepermission.RuntimePermissionListener

class SplashActivityPresenter(private val runtimePermissionListener: RuntimePermissionListener) :
    SplashActivityContract.Presenter {

    private var view: SplashActivityContract.View? = null
    private val MILLISECOND_HANDLER_DELAY_TIME=3000L

    override fun create() {
        view?.initUi()
        view?.starGifAnim()
        this.startHandler()
    }

    override fun setView(view: SplashActivityContract.View) {
        this.view = view
    }

    override fun startHandler() {
        Handler().postDelayed({
            checkLocationPermission()




        }, MILLISECOND_HANDLER_DELAY_TIME)

    }

    override fun checkLocationPermission() {
        if(runtimePermissionListener.hasAccessFineLocationPermission)
        {
            view?.intentToMainActivity()
        }else{
            view?.intentToLocationPermissionActivity()
        }
        view?.finishActivity()

    }



}