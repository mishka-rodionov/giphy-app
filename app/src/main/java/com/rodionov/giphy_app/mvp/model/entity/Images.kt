package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class Images(
    @SerializedName("fixed_height_still")
    val fixedHeightStill: ImageModel,

    @SerializedName("original_mp4")
    val originalMp4: ImageModel
) {
}