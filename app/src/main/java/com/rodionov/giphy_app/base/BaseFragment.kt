package com.rodionov.giphy_app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rodionov.giphy_app.R

/**
 * Created by rodionov on 25.11.2019.
 */
open abstract class BaseFragment: Fragment(), OnBackPressed{

    private var containerView: ViewGroup? = null
    private var contentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        containerView = inflater.inflate(R.layout.base_fragment, container, false) as ViewGroup
        contentView = inflater.inflate(getLayoutResource(), container, false)
        containerView?.addView(contentView)

        return containerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view = view)
        requestData()
    }

    override fun onBackPressed() {

    }

    protected abstract fun initViews(view: View)

    protected abstract fun getLayoutResource(): Int

    protected abstract fun requestData()

}