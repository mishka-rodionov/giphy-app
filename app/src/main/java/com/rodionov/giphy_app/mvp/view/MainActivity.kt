package com.rodionov.giphy_app.mvp.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.nitrico.lastadapter.LastAdapter
import com.rodionov.giphy_app.BR
import com.rodionov.giphy_app.R
import com.rodionov.giphy_app.app.GiphyApp
import com.rodionov.giphy_app.app.GlideApp
import com.rodionov.giphy_app.databinding.GifListItemBinding
import com.rodionov.giphy_app.mvp.presenter.ITrendGIFPresenter
import com.rodionov.giphy_app.mvp.view.item.GIFListItem
import com.rodionov.giphy_app.utils.Settings
import com.rodionov.giphy_app.utils.UIUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ITrendGIFView {

    @Inject
    lateinit var presenter: ITrendGIFPresenter

    val list = mutableListOf<GIFListItem>()
    val adapter = LastAdapter(list, BR.item)
        .map<GIFListItem, GifListItemBinding>(R.layout.gif_list_item){
            onBind {
                val url = list[it.adapterPosition].url
                val title = list[it.adapterPosition].title
                val imageView = it.binding.ivItemGifList
//                Log.d(Settings.TAG, "MainActivity onBind url $url")
//                Log.d(Settings.TAG, "MainActivity onBind url $title")
                val layoutParams = imageView.layoutParams
                layoutParams.width = UIUtils.getScreenWidthInPx(this@MainActivity) - UIUtils.convertDpToPixel(8F).toInt()
                val scaleFactor: Float = (UIUtils.getScreenWidthInPx(this@MainActivity) - UIUtils.convertDpToPixel(8F).toInt()).toFloat() / 200F
                layoutParams.height = (list[it.adapterPosition].height * scaleFactor).toInt()
                imageView.layoutParams = layoutParams
                it.binding.tvItemGifListTitle.text = title
                GlideApp.with(imageView.context)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            Log.d(Settings.TAG, "MainActivity listener onLoadFailed")
                            return false
                        }

                        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            Log.d(Settings.TAG, "MainActivity listener onResourceReady")
                            it.binding.pbItemGiftList.visibility = View.GONE
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        gifListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var currentLastVisibleItemIndex = -1
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val item = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if(item == (list.size - Settings.PREPARATION)){
                    if (currentLastVisibleItemIndex != item)
                    {
                        currentLastVisibleItemIndex = item
                        presenter.requestData(0, list.size.toLong() + 1)
                        Log.d(Settings.TAG, "MainActivity onScrolled $item")
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        injectDependencies()
        presenter.attachView(this)
        presenter.requestData(0,0)
    }

    override fun attach(presenter: com.rodionov.giphy_app.base.IBasePresenter) {

    }

    override fun detach() {

    }

    private fun initViews(){
        adapter.into(gifListRecyclerView)
        searchImageView.setOnClickListener {
            presenter.requestSearchData(searchEditText.text.toString(), 0, 0 )
        }
    }

    override fun updateView(data: MutableList<GIFListItem>) {
        Log.d(Settings.TAG, "MainActivity updateView")
//        list.clear()
        list.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun injectDependencies(){
        GiphyApp.getInjector()?.inject(this)
    }

    override fun showMessage(message: String) {
        Log.d(Settings.TAG, "MainActivity showMessage $message")
    }
}
