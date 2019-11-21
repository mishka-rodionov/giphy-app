package com.rodionov.giphy_app.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.nitrico.lastadapter.LastAdapter
import com.rodionov.giphy_app.BR
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.utils.Settings
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ITrendGIFView {

    @Inject
    lateinit var presenter: ITrendGIFPresenter

    val list = mutableListOf<Any>()
    val adapter = LastAdapter(list, BR.item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
        presenter.attachView(this)
//        presenter.detach()
        presenter.requestData()
    }

    override fun attach(presenter: com.rodionov.giphy_app.base.IBasePresenter) {

    }

    override fun detach() {

    }

    override fun updateView(data: MutableList<Any>) {

    }

    fun injectDependencies(){
        GiphyApp.getInjector()?.inject(this)
    }

    override fun showMessage(message: String) {
        Log.d(Settings.TAG, "MainActivity showMessage $message")
    }
}
