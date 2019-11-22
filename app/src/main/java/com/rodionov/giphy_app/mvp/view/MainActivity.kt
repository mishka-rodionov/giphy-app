package com.rodionov.giphy_app.mvp.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
                Log.d(Settings.TAG, "MainActivity onBind url $url")
                Log.d(Settings.TAG, "MainActivity onBind url $title")
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
        adapter.into(gifListRecyclerView)
        injectDependencies()
        presenter.attachView(this)
//        presenter.detach()
        presenter.requestData()
    }

    override fun attach(presenter: com.rodionov.giphy_app.base.IBasePresenter) {

    }

    override fun detach() {

    }

    override fun updateView(data: MutableList<GIFListItem>) {
        Log.d(Settings.TAG, "MainActivity updateView")
        adapter.into(gifListRecyclerView)
        list.clear()
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
