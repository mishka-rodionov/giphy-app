package com.rodionov.giphy_app.mvp.presenter

import com.rodionov.giphy_app.mvp.view.MainActivity

/**
 * Created by rodionov on 18.11.2019.
 */
class GIFPresenter: BasePresenter<MainActivity> {
    override fun attach(view: MainActivity) {
        view
    }

    override fun detach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}