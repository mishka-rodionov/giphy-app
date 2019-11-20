package com.rodionov.giphy_app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rodionov.giphy_app.network.ApiService
import com.rodionov.giphy_app.utils.Settings
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rodionov on 18.11.2019.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val timeout = 10
        return OkHttpClient.Builder()
//            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
//            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
//            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory() = GsonConverterFactory.create(
        GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
            .serializeSpecialFloatingPointValues()
            .create())

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideRxJava2Adapter() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJava2Adapter: RxJava2CallAdapterFactory) = Retrofit.Builder()
        .baseUrl(Settings.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2Adapter)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create<ApiService>(ApiService::class.java)
}