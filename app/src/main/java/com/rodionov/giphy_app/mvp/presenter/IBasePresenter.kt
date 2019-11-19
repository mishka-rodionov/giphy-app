package com.rodionov.giphy_app.mvp.presenter

import com.rodionov.giphy_app.mvp.view.BaseView

/**
 * Created by rodionov on 19.11.2019.
 */
interface IBasePresenter<ViewType: BaseView> {

    fun attach(view: ViewType)
    fun detach()

}