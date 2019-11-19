package com.rodionov.giphy_app.mvp.presenter

import com.rodionov.giphy_app.mvp.view.BaseView


/**
 * Created by rodionov on 18.11.2019.
 */
abstract class BasePresenter<V : BaseView>: IBasePresenter<V> {

    lateinit var view: V

    override fun attach(view: V) {
        this.view = view
    }

//    override fun detach() {
//    }
}