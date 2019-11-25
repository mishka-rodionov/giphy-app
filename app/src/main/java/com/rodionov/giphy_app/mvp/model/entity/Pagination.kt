package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by rodionov on 21.11.2019.
 */
class Pagination(
    @SerializedName("total_count")
    var totalCount: Long = 0,

    @SerializedName("count")
    var currentCount: Long = 0,

    @SerializedName("offset")
    var offsetIndex: Long = 0
) {

    fun hasMore() =
        currentCount + offsetIndex < totalCount

}