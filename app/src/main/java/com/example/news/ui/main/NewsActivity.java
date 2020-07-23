package com.example.news.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.news.Adapters.NewsAdapter;
import com.example.news.Models.NewsModel;
import com.example.news.R;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private final NewsAdapter newsAdapter = new NewsAdapter();
    private ShimmerRecyclerView shimmerRecyclerView;
    private MainActivityViewModel mainActivityViewModel;
    public DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = findViewById(R.id.toolbar);


       /* drawerLayout = findViewById(R.id.Drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.about);
        setSupportActionBar(toolbar);*/

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);
        shimmerRecyclerView = findViewById(R.id.recycler);
        shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getNews(getIntent().getStringExtra("category"));

        mainActivityViewModel.mutableLiveData.observe(this, new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                setData(newsModel.getArticles());
            }
        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mainActivityViewModel.getNews(getIntent().getStringExtra("category"));
            swipeRefreshLayout.setRefreshing(false);
        });


    }

    public void setData(List<NewsModel.ArticlesEntity> articles) {
        newsAdapter.setList(articles);
        newsAdapter.notifyDataSetChanged();
        shimmerRecyclerView.setAdapter(newsAdapter);


        Log.e("XXViewModel", "ArrayList Add Successfully    ");
    }

}
