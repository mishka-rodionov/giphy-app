package com.rodionov.giphy_app.di

import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.presenter.TrendGIFPresenter
import com.rodionov.giphy_app.mvp.view.activities.MainActivity
import com.rodionov.giphy_app.mvp.view.fragments.TrendGIFFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rodionov on 18.11.2019.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class, PresenterModule::class, InteractModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(trendGIFFragment: TrendGIFFragment)
    fun inject(interactor: TrendGIFInteractor)
    fun inject(presenter: TrendGIFPresenter)
}