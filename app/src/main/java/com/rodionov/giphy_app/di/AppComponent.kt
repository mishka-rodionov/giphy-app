package com.rodionov.giphy_app.di

import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.view.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rodionov on 18.11.2019.
 */
@Component(modules = arrayOf(NetworkModule::class, PresenterModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(interactor: TrendGIFInteractor)
}