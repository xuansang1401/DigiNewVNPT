package com.gpaddy.baseandroid.network

import com.gpaddy.baseandroid.data.model.api.NewsModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkRequest {
    //    http://20.36.46.207:8080/book/trending
//    https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Ftin-moi-nhat.rss
//    http://45.77.245.73/news/public/api/news/youtube
    @GET("/v1/api.json")
    fun getNewsByUrl(
        @Query("rss_url") rss_url: String,
    ): Observable<NewsModel>
    companion object {
        private const val BASE_URL = "https://api.rss2json.com/"
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