package com.example.news.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.Models.NewsModel;
import com.example.news.WebServices.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {


    MutableLiveData<NewsModel> mutableLiveData = new MutableLiveData<>();


    public void getNews(String category) {

            WebService.getInstance().getPO(category).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    Log.e("XXViewModel", t.getCause().getMessage());
                }
            });

      /*    Observable observable = WebService.getInstance().getPO(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
       observable.subscribe(o ->  mutableLiveData.setValue((NewsModel) o));

        RxJavaPlugins.setErrorHandler(e -> {
            Log.e("XXViewModelhandler", "onError: " + e.getMessage());
        });


        Observer<NewsModel> observer = new Observer<NewsModel>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.e("XXViewModel", "onSubscribe: ");
            }

            @Override
            public void onNext(NewsModel newsModel) {
                Log.e("XXViewModel", "onNext: " + "setValue");
                mutableLiveData.setValue(newsModel);
            }

            @Override
            public void onError(Throwable e) {

                Log.e("XXViewModel", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("XXViewModel", "onComplete: ");
            }
        };

        observable.subscribe(observer);
*/
        }


    }

