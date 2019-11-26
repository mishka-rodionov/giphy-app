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

    override fun requestData(limit: Long, offset: Long) {
        apiService.getTrending(Settings.API_KEY, Settings.LIMIT.toLong(), offset = offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val gifListItems = mutableListOf<GIFListItem>()
                it.gifObjectsList.forEach {
                    gifListItems.add(GIFListItem(title = it.title,
                        url = it.imagesListModel.fixedWidth.gifUrl,
                        height = it.imagesListModel.fixedWidth.height.toInt(),
                        videoURL = it.imagesListModel.originalMp4.mp4Url) )
                }
                gifListItems
            }
            .subscribeBy(
                onNext = {
                    interactorOutput?.receivedData(it)
                },
                onError = {
                    interactorOutput?.errorMessage(Settings.CHECK_INTERNET_CONNECTION)
                }

            )
    }

    override fun requestData(query: String, limit: Long, offset: Long) {
        apiService.getSearching(Settings.API_KEY, query = query, limit = Settings.LIMIT.toLong(), offset = offset)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val gifListItems = mutableListOf<GIFListItem>()
                it.gifObjectsList.forEach {
                    gifListItems.add(GIFListItem(title = it.title,
                        url = it.imagesListModel.fixedWidth.gifUrl,
                        height = it.imagesListModel.fixedWidth.height.toInt(),
                        videoURL = it.imagesListModel.originalMp4.mp4Url) )
                }
                gifListItems
            }
            .subscribeBy(
                onNext = {
                    if (offset != 0L)
                        interactorOutput?.receivedData(it)
                    else
                        interactorOutput?.receivedSearchData(it)
                },
                onError = {
                    interactorOutput?.errorMessage(Settings.CHECK_INTERNET_CONNECTION)
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
                        url = it.imagesListModel.fixedWidth.gifUrl,
                        height = it.imagesListModel.fixedWidth.height.toInt(),
                        videoURL = it.imagesListModel.originalMp4.mp4Url) )
                }
                gifListItems
            }
            .subscribeBy(
                onNext = {
                    if (offset != 0L)
                        interactorOutput?.receivedData(it)
                    else
                        interactorOutput?.receivedSearchData(it)
                },
                onError = {
                    interactorOutput?.errorMessage(Settings.CHECK_INTERNET_CONNECTION)
                }
            )
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}