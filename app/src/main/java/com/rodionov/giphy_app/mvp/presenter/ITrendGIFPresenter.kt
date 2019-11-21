package com.rodionov.giphy_app.mvp.presenter

import com.rodionov.giphy_app.base.IBasePresenter
import com.rodionov.giphy_app.mvp.view.ITrendGIFView

/**
 * Created by rodionov on 19.11.2019.
 */
interface ITrendGIFPresenter: IBasePresenter {
    fun requestData()
}