package com.rodionov.giphy_app.mvp.view

import com.rodionov.giphy_app.base.IBaseView
import com.rodionov.giphy_app.mvp.view.item.GIFListItem

/**
 * Created by rodionov on 19.11.2019.
 */
interface ITrendGIFView: IBaseView {
    fun updateView(data: MutableList<GIFListItem>)
    fun updateSearchView(data: MutableList<GIFListItem>)
}