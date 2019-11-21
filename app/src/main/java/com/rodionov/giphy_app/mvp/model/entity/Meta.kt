package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class Meta(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("msg")
    val message: String? = null,
    @SerializedName("response_id")
    val responseId: String? = null
)