package com.example.news.WebServices;

import com.example.news.Models.NewsModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("top-headlines")
    Observable<NewsModel> getPosts(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apikey);

}
