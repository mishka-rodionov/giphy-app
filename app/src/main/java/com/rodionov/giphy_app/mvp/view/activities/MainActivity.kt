package com.rodionov.giphy_app.mvp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodionov.giphy_app.base.BaseRouter
import com.rodionov.giphy_app.base.BaseFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rodionov.giphy_app.R.layout.activity_main2)
        BaseRouter.openFirstFragment(fragmentManager = supportFragmentManager)
    }

    override fun onBackPressed() {
        tellFragments()
        super.onBackPressed()
    }

    private fun tellFragments() {
        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f != null && f is BaseFragment)
                f.onBackPressed()
        }
    }

}
