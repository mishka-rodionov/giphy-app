package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
interface IBasePresenter {
    fun attachView(view: IBaseView)
    fun detachView()
    fun attachInteractor(interactor: IBaseInteractor)
    fun detachInteractor()
}