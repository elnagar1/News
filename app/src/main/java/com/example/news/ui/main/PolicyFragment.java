package com.example.news.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.news.Adapters.NewsAdapter;
import com.example.news.Models.NewsModel;
import com.example.news.utils.MyAsyncTask;
import com.example.news.R;
import com.example.news.utils.ops;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;


public class PolicyFragment extends Fragment  implements ops {
    MyAsyncTask myAsyncTask;
    private NewsAdapter newsAdapter;
    private ShimmerRecyclerView shimmerRecyclerVie;
    private MainActivityViewModel mainActivityViewModel;
    public DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;

    final String url = "https://www.youm7.com/Section/%D8%B3%D9%8A%D8%A7%D8%B3%D8%A9/319/1";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shimmerRecyclerVie = view.findViewById(R.id.recycler);
        shimmerRecyclerVie.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter();
        shimmerRecyclerVie.setAdapter(newsAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
        });

         myAsyncTask =new MyAsyncTask(this,url);
                myAsyncTask.execute();


    }

    @Override
    public void onStop() {
        super.onStop();
        myAsyncTask.cancel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_policy, container, false);
        return v;
    }

    @Override
    public void get(List<NewsModel.ArticlesEntity> articlesEntities) {

        // List.add(articlesEntities.get(0));
        // newsAdapter.setList(List);
        newsAdapter.setList(articlesEntities);
    }

    @Override
    public void get2(List<NewsModel.ArticlesEntity> asList) {

        newsAdapter.setArticle(asList.get(0));
    }


}