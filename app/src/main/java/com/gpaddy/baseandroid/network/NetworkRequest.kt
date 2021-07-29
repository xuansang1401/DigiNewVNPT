package com.gpaddy.baseandroid.network

import com.gpaddy.baseandroid.data.model.api.NewsModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetworkRequest {
    //    http://20.36.46.207:8080/book/trending

    @GET("api/channels/new/app/get/TOP")
    fun getAllNews(): Observable<List<NewsModel>>
    //    http://78.128.60.15:8080/api/channels/new/app/get/TOP
    companion object {
        private const val BASE_URL = "http://78.128.60.15:8080/"
        fun create(): NetworkRequest {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkRequest::class.java)
        }
    }
}