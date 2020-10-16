package com.ogungor.kotlinmapplaces.splash

interface SplashActivityContract {

    interface Presenter {

        fun create()

        fun setView(view: SplashActivityContract.View)

        fun startHandler()

        fun checkLocationPermission()



    }


    interface View {
        fun intentToMainActivity()

        fun intentToLocationPermissionActivity()

        fun initUi()

        fun starGifAnim()

        fun finishActivity()
    }
}