package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class GIFObject(
    @SerializedName("title")
    val title: String,

    @SerializedName("images")
    val imagesListModel: Images
)