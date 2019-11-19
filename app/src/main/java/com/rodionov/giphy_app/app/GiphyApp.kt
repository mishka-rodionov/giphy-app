package com.rodionov.giphy_app.app

import android.app.Application
import android.util.Log
import com.rodionov.giphy_app.di.AppComponent
import com.rodionov.giphy_app.di.DaggerAppComponent
import com.rodionov.giphy_app.di.NetworkModule
import com.rodionov.giphy_app.di.PresenterModule
import com.rodionov.giphy_app.utils.Settings

/**
 * Created by rodionov on 18.11.2019.
 */
class GiphyApp : Application() {

    companion object {
        private var appComponent: AppComponent? = null
        fun getInjector() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(Settings.TAG, "GiphyApp onCreate")
        appComponent = initDagger()
    }

    private fun initDagger(): AppComponent = DaggerAppComponent.builder()
        .presenterModule(PresenterModule())
        .networkModule(NetworkModule())
        .build()
}