package com.rodionov.giphy_app.mvp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.base.BaseRouter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        BaseRouter.openFirstFragment(fragmentManager = supportFragmentManager)
    }

}
