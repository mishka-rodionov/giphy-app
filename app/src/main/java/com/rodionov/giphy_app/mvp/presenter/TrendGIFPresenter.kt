package com.rodionov.giphy_app.mvp.presenter

import android.util.Log
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.base.BasePresenter
import com.rodionov.giphy_app.base.IBaseInteractor
import com.rodionov.giphy_app.base.IBaseView
import com.rodionov.giphy_app.mvp.model.interactor.ITrendGIFInteractor
import com.rodionov.giphy_app.mvp.model.interactor.ItrendGIFInteractorOutput
import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.view.ITrendGIFView
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

//    override fun detachView() {
//
//    }

//    override fun attachInteractor(interactor: IBaseInteractor) {
//        super.attachInteractor(interactor)
//    }

//    override fun detachInteractor() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//    override fun attach(view: ITrendGIFView) {
//        super.attach(view)
//        view.showMessage("123456789")
//        Log.d(Settings.TAG, "From TrendGIFPresenter attach")
//    }

//    override fun detach() {
//        Log.d(Settings.TAG, "From TrendGIFPresenter detach")
//    }

    override fun requestData() {
        Log.d(Settings.TAG, "From TrendGIFPresenter requestData")
        interactor?.requestData()

    }

    override fun receivedData() {
        Log.d(Settings.TAG, "From TrendGIFPresenter receivedData")
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}