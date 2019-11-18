package com.rodionov.giphy_app.mvp.presenter

import com.rodionov.giphy_app.mvp.view.BaseView


/**
 * Created by rodionov on 18.11.2019.
 */
interface BasePresenter<in V: BaseView> {

    fun attach(view: V)
    fun detach()
}