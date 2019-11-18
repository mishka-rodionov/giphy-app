package com.rodionov.giphy_app.app

import android.app.Application
import com.rodionov.giphy_app.di.AppComponent

/**
 * Created by rodionov on 18.11.2019.
 */
class GiphyApp : Application() {

    companion object{
        private var appComponent: AppComponent? = null
        fun getInjector() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
    }

    private fun initDagger(): AppComponent = DaggerAppComponent.builder().build()
}