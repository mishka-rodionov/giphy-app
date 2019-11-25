package com.rodionov.giphy_app.mvp.presenter

import android.util.Log
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.base.BasePresenter
import com.rodionov.giphy_app.base.IBaseView
import com.rodionov.giphy_app.mvp.model.interactor.ITrendGIFInteractor
import com.rodionov.giphy_app.mvp.model.interactor.ItrendGIFInteractorOutput
import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.view.ITrendGIFView
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import com.rodionov.giphy_app.utils.Settings
import javax.inject.Inject

/**
 * Created by rodionov on 18.11.2019.
 */
class TrendGIFPresenter: BasePresenter<ITrendGIFView, ITrendGIFInteractor>(), ITrendGIFPresenter, ItrendGIFInteractorOutput {

    @Inject
    lateinit var trendInteractor: TrendGIFInteractor

    init {
        injectDependency()
        super.attachInteractor(trendInteractor)
    }

    override fun attachView(view: IBaseView) {
        super.attachView(view)
        view.showMessage("123456789")
        Log.d(Settings.TAG, "From TrendGIFPresenter attach")
    }


    override fun requestData(limit: Int, offset: Long) {
        Log.d(Settings.TAG, "From TrendGIFPresenter requestData")
        interactor?.requestData(limit, offset)

    }

    override fun requestSearchData(query: String, limit: Long, offset: Long) {
        interactor?.requestSearchData(query = query, limit = limit, offset = offset)
    }

    override fun receivedData(data: MutableList<GIFListItem>) {
        view?.updateView(data = data)
        Log.d(Settings.TAG, "From TrendGIFPresenter receivedData")
    }

    override fun receivedSearchData(data: MutableList<GIFListItem>) {
        view?.updateView(data = data)
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}