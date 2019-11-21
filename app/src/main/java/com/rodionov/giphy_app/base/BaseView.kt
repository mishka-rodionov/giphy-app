package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
abstract class BaseView: IBaseView {

    var presenter: IBasePresenter?

    init {
        presenter = null
    }

    override fun attach(presenter: IBasePresenter) {
        this.presenter = presenter
    }

    override fun detach() {
        this.presenter = null
    }
}