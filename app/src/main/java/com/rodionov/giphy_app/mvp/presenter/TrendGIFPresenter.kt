package com.rodionov.giphy_app.mvp.presenter

import android.util.Log
import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.view.ITrendGIFView
import com.rodionov.giphy_app.utils.Settings

/**
 * Created by rodionov on 18.11.2019.
 */
class TrendGIFPresenter: BasePresenter<ITrendGIFView>(), ITrendGIFPresenter {

    override fun attach(view: ITrendGIFView) {
        super.attach(view)
        view.showMessage("123456789")
        Log.d(Settings.TAG, "From TrendGIFPresenter attach")
    }

    override fun detach() {
        Log.d(Settings.TAG, "From TrendGIFPresenter detach")
    }

    override fun requestData() {
        Log.d(Settings.TAG, "From TrendGIFPresenter requestData")
        val interactor = TrendGIFInteractor()
        interactor.requestData()

    }
}