package com.rodionov.giphy_app.mvp.model.interactor

import com.rodionov.giphy_app.db.DB

/**
 * Created by rodionov on 18.11.2019.
 */
abstract class BaseInteractor<InteractorOutput>{

//    lateinit var db: DB
    var interactorOutput: InteractorOutput?

    init {
        interactorOutput = null
    }

    fun setOutput(output: InteractorOutput){
        this.interactorOutput = output
    }

}