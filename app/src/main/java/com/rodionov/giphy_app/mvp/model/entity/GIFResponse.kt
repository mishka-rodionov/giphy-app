package com.rodionov.giphy_app.mvp.model.entity

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by rodionov on 18.11.2019.
 */
class GIFResponse(
    val meta: Meta,

    @SerializedName("data")
    val gifObjectsList: ArrayList<GIFObject> = arrayListOf(),

    var pagination: Pagination)