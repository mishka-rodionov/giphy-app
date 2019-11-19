package com.rodionov.giphy_app.mvp.model.interactor

import android.util.Log
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.mvp.presenter.TrendGIFPresenter
import com.rodionov.giphy_app.network.ApiService
import com.rodionov.giphy_app.utils.Settings
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by rodionov on 18.11.2019.
 */
class TrendGIFInteractor: BaseInteractor<TrendGIFPresenter>(), ITrendGIFInteractor {

    @Inject
    lateinit var apiService: ApiService

    init {
        injectDependency()
    }

    override fun requestData() {
        Log.d(Settings.TAG, "From TrendGIFInteractor requestData")
        apiService.getTrending(Settings.API_KEY, 5, 0)
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}