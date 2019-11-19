package com.rodionov.giphy_app.di

import android.util.Log
import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.mvp.presenter.TrendGIFPresenter
import com.rodionov.giphy_app.utils.Settings
import dagger.Module
import dagger.Provides

/**
 * Created by rodionov on 19.11.2019.
 */
@Module
class PresenterModule {

    @Provides
    fun providesTrendGIFPresenter(): ITrendGIFPresenter {
        Log.d(Settings.TAG, "providesTrendGIFPresenter")
        return TrendGIFPresenter()
    }
}