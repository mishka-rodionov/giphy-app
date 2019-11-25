package com.rodionov.giphy_app.mvp.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.base.BaseFragment
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import kotlinx.android.synthetic.main.full_gif_fragment.*

/**
 * Created by rodionov on 25.11.2019.
 */
class FullGIFFragment : BaseFragment() {

    companion object {

        const val GIF_ITEM = "GIF_ITEM"

        fun newInstance(item: GIFListItem): FullGIFFragment {
            val fragment = FullGIFFragment()
            val bundle = Bundle()
            bundle.putParcelable(GIF_ITEM, item)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var currentGif: GIFListItem? = null

    fun getCurrentGIF(): GIFListItem? = arguments?.getParcelable(GIF_ITEM)

    override fun initViews(view: View) {
        playListener()
        currentGif = getCurrentGIF()
        if (currentGif != null)
            displayGif()
    }

    override fun getLayoutResource(): Int {
        return R.layout.full_gif_fragment
    }

    override fun requestData() {
    }

    private fun playListener() =
        fullGIFVideoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            fullGIFCardView.visibility = View.VISIBLE
            fullGIFVideoView.start()
        }

    private fun displayGif() {
        fullGIFCardView.visibility = View.INVISIBLE
        fullGIFTitle.visibility = View.VISIBLE
        if (currentGif?.title.isNullOrBlank())
            fullGIFTitle.visibility = View.GONE
        fullGIFTitle.text = currentGif?.title
        fullGIFVideoView.setVideoURI(Uri.parse(currentGif?.videoURL))
        fullGIFVideoView.requestFocus()

    }

}