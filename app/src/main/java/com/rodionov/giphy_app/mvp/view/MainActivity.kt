package com.rodionov.giphy_app.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodionov.giphy_app.R

class MainActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showMessage(message: String) {

    }
}
