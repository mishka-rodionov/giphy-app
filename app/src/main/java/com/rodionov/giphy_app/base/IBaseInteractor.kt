package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
interface IBaseInteractor {
    fun attachOutput(interactorOutput: IBasePresenter?)
    fun detachOutput()
}