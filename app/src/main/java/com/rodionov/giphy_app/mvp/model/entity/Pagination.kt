package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class Pagination(
    @SerializedName("total_count")
    private var totalCount: Long,

    @SerializedName("count")
    private var currentCount: Long,

    @SerializedName("offset")
    var offsetIndex: Long
) {
}