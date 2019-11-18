package com.rodionov.giphy_app.di

import dagger.Component

/**
 * Created by rodionov on 18.11.2019.
 */
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject()
}