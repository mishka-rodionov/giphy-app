package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class ImageModel(
    @SerializedName("url")
    val gifUrl: String = "",

    @SerializedName("mp4")
    val mp4Url: String = "",

    val width: String = "",
    val height: String = ""
) {
}