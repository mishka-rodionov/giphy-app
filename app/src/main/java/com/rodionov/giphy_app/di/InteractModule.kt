package com.rodionov.giphy_app.di

import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.network.ApiService
import dagger.Module
import dagger.Provides

/**
 * Created by rodionov on 19.11.2019.
 */
@Module
class InteractModule {

    @Provides
    fun provideTrendGIFInteractor(api: ApiService): TrendGIFInteractor {
        return TrendGIFInteractor(api)
    }

}