package com.rodionov.giphy_app.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.mvp.view.fragments.TrendGIFFragment

/**
 * Created by rodionov on 25.11.2019.
 */
class BaseRouter {
    companion object{
        fun changeFragment(fragmentManager: FragmentManager, fragment: Fragment){
            fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(fragment.javaClass.name)
                .replace(R.id.contentFrame, fragment, fragment.javaClass.name)
                .commit()
        }

        fun openFirstFragment(fragmentManager: FragmentManager){
            val fragment = TrendGIFFragment()
            changeFragment(fragmentManager = fragmentManager, fragment = fragment)
        }
    }
}