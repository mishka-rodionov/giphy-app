package com.rodionov.giphy_app.mvp.model.interactor

import com.rodionov.giphy_app.base.IBaseInteractor

/**
 * Created by rodionov on 20.11.2019.
 */
interface ITrendGIFInteractor: IBaseInteractor {
    fun requestData()
}