package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
abstract class BasePresenter<ViewType: IBaseView, InteractorType: IBaseInteractor>: IBasePresenter {

    var view: ViewType?
    var interactor: InteractorType?

    init {
        view = null
        interactor = null
    }

    override fun attachView(view: IBaseView) {
        this.view = view as ViewType
    }

    override fun detachView() {
        this.view = null
    }

    override fun attachInteractor(interactor: IBaseInteractor) {
        interactor.attachOutput(this)
        this.interactor = interactor as InteractorType
    }

    override fun detachInteractor() {
        this.interactor = null
    }
}