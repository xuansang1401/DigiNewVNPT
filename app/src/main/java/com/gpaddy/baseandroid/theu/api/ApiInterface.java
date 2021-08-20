package com.gpaddy.baseandroid.theu.api;


import com.gpaddy.baseandroid.R;
import com.gpaddy.baseandroid.theu.ttvnpt.*;
import com.gpaddy.baseandroid.theu.model.*;
import com.gpaddy.baseandroid.theu.DAO.*;
import com.gpaddy.baseandroid.theu.api.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //v2/top-headlines
    //    //https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Ftin-moi-nhat.rss
    @GET("v1/api.json")
    Call<NewsModel> getNewByUrl (@Query("rss_url") String rss_url);
//v2/everything
    //https://newsapi.org/v2/everything?language=vi&q=hanoi&domains=vnexpress.net&apiKey=3d30621d12254ce5864d0bad40094a88
    //https://newsapi.org/v2/everything?q=tesla&from=2021-07-08&sortBy=publishedAt&apiKey=3d30621d12254ce5864d0bad40094a88
    @GET("v2/everything")
    Call<News> getNews(
            @Query("q") String keysearch,
            @Query("language") String language,
            @Query("domains") String domains,
            @Query("apiKey") String apiKey
    );
}
