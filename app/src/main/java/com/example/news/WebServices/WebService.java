package com.example.news.WebServices;

import com.example.news.Models.NewsModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    public static final String BaseUrl = "http://newsapi.org/v2/";
    static Api api;
    static WebService INSTANCE;

    public WebService() {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    api = retrofit.create(Api.class);




    }

    public static WebService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebService();
        }

        return INSTANCE;
    }

    public static Observable<NewsModel> getPO(String category) {
        return api.getPosts("eg", category, "0e52b6c2d0c64244b6e608a3a7b036ad");
    }

}
