package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
interface IBaseView {
    fun attach(presenter: IBasePresenter)
    fun detach()
    fun showMessage(message: String)

}