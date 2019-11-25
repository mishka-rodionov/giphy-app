package com.rodionov.giphy_app.network

import com.rodionov.giphy_app.mvp.model.entity.GIFResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rodionov on 18.11.2019.
 */
interface ApiService {

    @GET("/v1/gifs/trending")
    fun getTrending(@Query("api_key") apiKey:String, @Query("limit") limit:Long, @Query("offset") offset:Long): Observable<GIFResponse>

    @GET("/v1/gifs/search")
    fun getSearching(@Query("api_key") apiKey:String,
                     @Query("q") query:String,
                     @Query("limit") limit:Long,
                     @Query("offset") offset:Long): Observable<GIFResponse>

}