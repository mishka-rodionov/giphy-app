package com.rodionov.giphy_app.mvp.view.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.nitrico.lastadapter.LastAdapter
import com.google.android.material.snackbar.Snackbar
import com.rodionov.giphy_app.BR
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.app.GlideApp
import com.rodionov.giphy_app.base.BaseFragment
import com.rodionov.giphy_app.databinding.GifListItemBinding
import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.mvp.view.ITrendGIFView
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import com.rodionov.giphy_app.utils.Settings
import com.rodionov.giphy_app.utils.UIUtils
import kotlinx.android.synthetic.main.trend_gif_fragment.*
import javax.inject.Inject

/**
 * Created by rodionov on 25.11.2019.
 */
class TrendGIFFragment : BaseFragment(), ITrendGIFView {

    @Inject
    lateinit var presenter: ITrendGIFPresenter
    var searchString: String? = null

    val list = mutableListOf<GIFListItem>()
    val adapter = LastAdapter(list, BR.item)
        .map<GIFListItem, GifListItemBinding>(R.layout.gif_list_item) {
            onBind {
                val url = list[it.adapterPosition].url
                val title = list[it.adapterPosition].title
                val imageView = it.binding.ivItemGifList
                val layoutParams = imageView.layoutParams
                layoutParams.width =
                    UIUtils.getScreenWidthInPx(context as Context) - UIUtils.convertDpToPixel(8F).toInt()
                val scaleFactor: Float =
                    (UIUtils.getScreenWidthInPx(context as Context) - UIUtils.convertDpToPixel(8F).toInt()).toFloat() / Settings.FIXED_WIDTH
                layoutParams.height = (list[it.adapterPosition].height * scaleFactor).toInt()
                imageView.layoutParams = layoutParams
                it.binding.tvItemGifListTitle.text = title
                GlideApp.with(imageView.context)
                    .load(url)
                    .placeholder(R.drawable.cock)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            it.binding.pbItemGiftList.visibility = View.GONE
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }

            onClick {
                presenter.onGIFClicked(list[it.adapterPosition], fragmentManager as FragmentManager)
            }
        }

    override fun initViews(view: View) {
        adapter.into(gifListRecyclerView)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (clear.visibility == View.INVISIBLE && !p0?.trim().isNullOrEmpty())
                    clear.visibility = View.VISIBLE
                if (clear.visibility == View.VISIBLE && p0?.trim().isNullOrEmpty())
                    clear.visibility = View.INVISIBLE
            }
        })

        searchGIF()

        clearSearchEditText()

        addOnScrollListenerToRecyclerView()

        injectDependencies()
        presenter.attachView(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.trend_gif_fragment
    }

    override fun requestData() {
        if (list.size == 0)
            presenter.requestData("", 0, 0)
    }

    override fun updateView(data: MutableList<GIFListItem>) {
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun updateSearchView(data: MutableList<GIFListItem>) {
        list.clear()
        list.addAll(data)
        adapter.notifyDataSetChanged()
        gifListRecyclerView.layoutManager?.scrollToPosition(0)
    }

    override fun onBackPressed() {
        activity?.finish()
        super.onBackPressed()
    }

    override fun showMessage(message: String) {
        Snackbar.make(
            activity?.findViewById(R.id.contentFrame) as View,
            message,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("RETRY", object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    presenter.requestData(searchString, 0, list.size.toLong() + 1)
                }
            })
            .setActionTextColor(resources.getColor(R.color.colorPrimary))
            .show()
    }

    private fun injectDependencies() {
        GiphyApp.getInjector()?.inject(this)
    }

    private fun searchGIF() {
        searchImageView.setOnClickListener {
            hideKeyboard()
            searchString = searchEditText.text.toString().trim()
            if (!searchString.isNullOrEmpty()) {
                presenter.requestData(searchEditText.text.toString(), 0, 0)
            } else {
            }
        }
    }

    private fun clearSearchEditText() {
        clear.setOnClickListener {
            searchEditText.text.clear()
            if (!searchString.isNullOrEmpty()) {
                searchString = ""
                list.clear()
                adapter.notifyDataSetChanged()
                presenter.requestData("", 0, 0)
            }
        }
    }

    private fun addOnScrollListenerToRecyclerView() {
        gifListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var currentLastVisibleItemIndex = -1
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val item =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (item == (list.size - Settings.PREPARATION)) {
                    if (currentLastVisibleItemIndex != item) {
                        currentLastVisibleItemIndex = item
                        presenter.requestData(searchString, 0, list.size.toLong() + 1)
                    }
                }
            }
        })
    }

}