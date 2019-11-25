package com.rodionov.giphy_app.mvp.model.interactor

import com.rodionov.giphy_app.mvp.view.item.GIFListItem

/**
 * Created by rodionov on 21.11.2019.
 */
interface ItrendGIFInteractorOutput {
    fun receivedData(data: MutableList<GIFListItem>)
    fun receivedSearchData(data: MutableList<GIFListItem>)
}