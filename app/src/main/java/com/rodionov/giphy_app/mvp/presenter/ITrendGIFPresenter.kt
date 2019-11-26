package com.rodionov.giphy_app.mvp.presenter

import androidx.fragment.app.FragmentManager
import com.rodionov.giphy_app.base.IBasePresenter
import com.rodionov.giphy_app.mvp.view.item.GIFListItem

/**
 * Created by rodionov on 19.11.2019.
 */
interface ITrendGIFPresenter: IBasePresenter {
    fun requestData(query: String?, limit: Long, offset: Long = 0)
    fun onGIFClicked(item: GIFListItem, fragmentManager: FragmentManager)
}