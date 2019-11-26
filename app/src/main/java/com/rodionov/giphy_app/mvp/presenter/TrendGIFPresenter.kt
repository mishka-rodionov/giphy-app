package com.rodionov.giphy_app.mvp.presenter

import androidx.fragment.app.FragmentManager
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.base.BasePresenter
import com.rodionov.giphy_app.base.BaseRouter
import com.rodionov.giphy_app.mvp.model.interactor.ITrendGIFInteractor
import com.rodionov.giphy_app.mvp.model.interactor.ItrendGIFInteractorOutput
import com.rodionov.giphy_app.mvp.model.interactor.TrendGIFInteractor
import com.rodionov.giphy_app.mvp.view.ITrendGIFView
import com.rodionov.giphy_app.mvp.view.fragments.FullGIFFragment
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
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

    override fun requestData(query: String?, limit: Long, offset: Long) {
        if (query.isNullOrEmpty())
            interactor?.requestData(limit = limit, offset = offset)
        else
            interactor?.requestData(query = query, limit = limit, offset = offset)

    }

    override fun requestSearchData(query: String, limit: Long, offset: Long) {
        interactor?.requestSearchData(query = query, limit = limit, offset = offset)
    }

    override fun receivedData(data: MutableList<GIFListItem>) {
        view?.updateView(data = data)
    }

    override fun receivedSearchData(data: MutableList<GIFListItem>) {
        view?.updateSearchView(data = data)
    }

    override fun onGIFClicked(item: GIFListItem, fragmentManager: FragmentManager) {
        val fragment = FullGIFFragment.newInstance(item = item)
        BaseRouter.changeFragment(
            fragmentManager = fragmentManager,
            fragment = fragment)
    }

    override fun errorMessage(message: String) {
        view?.showMessage(message)
    }

    private fun injectDependency(){
        GiphyApp.getInjector()?.inject(this)
    }
}