package com.rodionov.giphy_app.mvp.model.interactor

import com.rodionov.giphy_app.base.IBaseInteractor

/**
 * Created by rodionov on 20.11.2019.
 */
interface ITrendGIFInteractor: IBaseInteractor {
    fun requestData(limit: Long, offset: Long = 0)
    fun requestData(query: String, limit: Long, offset: Long = 0)
    fun requestSearchData(query: String, limit: Long, offset: Long = 0)
}