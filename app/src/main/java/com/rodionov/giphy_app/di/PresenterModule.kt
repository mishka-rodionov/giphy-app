package com.rodionov.giphy_app.di

import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.mvp.presenter.TrendGIFPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by rodionov on 19.11.2019.
 */
@Module
class PresenterModule {

    @Provides
    fun providesTrendGIFPresenter(): ITrendGIFPresenter {
        return TrendGIFPresenter()
    }
}