package com.rodionov.giphy_app.mvp.model.interactor

import com.rodionov.giphy_app.db.DB

/**
 * Created by rodionov on 18.11.2019.
 */
class BaseInteractor<InteractorOutput>(val db: DB,
                                       val interactorOutput: InteractorOutput)