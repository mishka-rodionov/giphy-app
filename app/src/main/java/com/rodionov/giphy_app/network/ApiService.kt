package com.rodionov.giphy_app.network

import com.rodionov.giphy_app.mvp.model.entity.TrendGIFResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rodionov on 18.11.2019.
 */
interface ApiService {

    @GET("/v1/gifs/trending")
    fun getTrending(@Query("api_key") apiKey:String, @Query("limit") limit:Long, @Query("offset") offset:Long): Observable<TrendGIFResponse>

    @GET("/v1/gifs/random")
    fun getRandom(@Query("api_key") apiKey:String): Observable<RandomResponse>

}