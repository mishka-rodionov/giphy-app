package com.rodionov.giphy_app.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.mvp.presenter.IBasePresenter
import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.utils.Settings
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ITrendGIFView {

    @Inject
    lateinit var presenter: ITrendGIFPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        presenter.attach(this)
        presenter.detach()
        presenter.requestData()
    }

    fun injectDependencies(){
        GiphyApp.getInjector()?.inject(this)
    }

    override fun showMessage(message: String) {
        Log.d(Settings.TAG, "MainActivity showMessage $message")
    }
}
