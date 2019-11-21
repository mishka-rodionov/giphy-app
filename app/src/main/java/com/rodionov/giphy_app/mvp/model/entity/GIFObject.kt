package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class GIFObject(
    @SerializedName("title")
    private val title: String,

    @SerializedName("images")
    private val imagesListModel: Images
) {
}