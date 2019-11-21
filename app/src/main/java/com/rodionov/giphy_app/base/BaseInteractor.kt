package com.rodionov.giphy_app.base

/**
 * Created by rodionov on 21.11.2019.
 */
abstract class BaseInteractor<InteractorOutput>: IBaseInteractor {

    var interactorOutput: InteractorOutput?

    init {
        interactorOutput = null
    }

    override fun attachOutput(interactorOutput: IBasePresenter?) {
        this.interactorOutput = interactorOutput as InteractorOutput
    }

    override fun detachOutput() {

    }
}