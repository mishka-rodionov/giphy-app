package com.rodionov.giphy_app.mvp.model.interactor

import android.util.Log
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.base.BaseInteractor
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import com.rodionov.giphy_app.network.ApiService
import com.rodionov.giphy_app.utils.Settings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
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

    override fun requestData(limit: Int, offset: Long) {
        Log.d(Settings.TAG, "From TrendGIFInteractor requestData")
        apiService.getTrending(Settings.API_KEY, Settings.LIMIT.toLong(), offset = offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Log.d(Settings.TAG, "From TrendGIFInteractor requestData pagination")
                Log.d(Settings.TAG, "offsetIndex ${it.pagination.offsetIndex}")
                Log.d(Settings.TAG, "totalCount ${it.pagination.totalCount}")
                Log.d(Settings.TAG, "currentCount ${it.pagination.currentCount}")
                val gifListItems = mutableListOf<GIFListItem>()
                it.gifObjectsList.forEach {
                    gifListItems.add(GIFListItem(title = it.title,
                        url = it.imagesListModel.fixedHeight.gifUrl,
                        height = it.imagesListModel.fixedHeight.height.toInt()) )
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

    override fun requestSearchData(query: String, limit: Long, offset: Long) {
        apiService.getSearching(Settings.API_KEY, query = query, limit = Settings.LIMIT.toLong(), offset = offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val gifListItems = mutableListOf<GIFListItem>()
                it.gifObjectsList.forEach {
                    gifListItems.add(GIFListItem(title = it.title,
                        url = it.imagesListModel.fixedHeight.gifUrl,
                        height = it.imagesListModel.fixedHeight.height.toInt()) )
                }
                gifListItems
            }
            .subscribeBy(
                onNext = {
                    interactorOutput?.receivedSearchData(it)
                },
                onError = {}
            )
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}