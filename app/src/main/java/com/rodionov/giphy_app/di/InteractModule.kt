package com.rodionov.giphy_app.di

import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by rodionov on 19.11.2019.
 */
@Module
class InteractModule {

    @Provides
    fun provideTrendGIFInteractor(): TrendGIFInteractor {
        return TrendGIFInteractor()
    }

}