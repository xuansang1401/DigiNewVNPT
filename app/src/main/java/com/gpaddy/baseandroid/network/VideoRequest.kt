package com.gpaddy.baseandroid.network

import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.data.model.api.VideoModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoRequest {
//    http://45.77.245.73/news/public/api/news/youtube
    @GET("/news/public/api/news/youtube")
    fun getVideo(): Observable<VideoModel>
    companion object {
        private const val BASE_URL = " http://45.77.245.73"
        fun create(): VideoRequest {
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
                .create(VideoRequest::class.java)
        }
    }
}