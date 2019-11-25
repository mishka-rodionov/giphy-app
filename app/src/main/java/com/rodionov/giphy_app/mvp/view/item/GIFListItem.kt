package com.rodionov.giphy_app.mvp.view.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by rodionov on 22.11.2019.
 */
@Parcelize
class GIFListItem(val title: String = "",
                  val url: String = "",
                  val videoURL: String = "",
                  val height: Int = 0): Parcelable