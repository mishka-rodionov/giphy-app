package com.rodionov.giphy_app.mvp.model.interactor

import android.util.Log
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.base.BaseInteractor
import com.rodionov.giphy_app.base.IBasePresenter
import com.rodionov.giphy_app.mvp.presenter.TrendGIFPresenter
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import com.rodionov.giphy_app.network.ApiService
import com.rodionov.giphy_app.utils.Settings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by rodionov on 18.11.2019.
 */
class TrendGIFInteractor(val api: ApiService): BaseInteractor<ItrendGIFInteractorOutput>(), ITrendGIFInteractor {

    @Inject
    lateinit var apiService: ApiService

    init {
        injectDependency()
    }

    override fun requestData() {
        Log.d(Settings.TAG, "From TrendGIFInteractor requestData")
        apiService.getTrending(Settings.API_KEY, 5, 0)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val gifListItems = mutableListOf<GIFListItem>()
//                Log.d(Settings.TAG, "on method map gif list size ${it.gifObjectsList.size}")
//                Log.d(Settings.TAG, "on method map message ${it.meta.message}")
//                Log.d(Settings.TAG, "on method map pagination offsetindex ${it.pagination.offsetIndex}")
                it.gifObjectsList.forEach {
                    gifListItems.add(GIFListItem(title = it.title, url = it.imagesListModel.fixedHeightStill.gifUrl))
                }
                gifListItems
            }
            .subscribeBy(
                onNext = {
                    Log.d(Settings.TAG, "From TrendGIFInteractor subscribeBy onNext")
                    interactorOutput?.receivedData(it)

                },
                onError = {
                    Log.d(Settings.TAG, "From TrendGIFInteractor subscribeBy onError")
                    Log.d(Settings.TAG, it.message)
                }

            )
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}