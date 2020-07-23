package com.example.news.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.Models.NewsModel;
import com.example.news.R;
import com.example.news.ui.main.DetailActivity;
import com.example.news.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<NewsModel.ArticlesEntity> articlesList = new ArrayList<>();
    private Context context;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newscard, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel.ArticlesEntity articlesEntity = articlesList.get(position);

        holder.title.setText(articlesEntity.getTitle());
        holder.time.setText(articlesEntity.getPublishedAt().replace("T", "  "));
        holder.details.setText(articlesEntity.getDescription());

        Glide.with(context).load(articlesEntity.getUrlToImage()).into(holder.newsImg);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public void setArticle(NewsModel.ArticlesEntity articles) {

        this.articlesList.add(articles);
        notifyItemInserted(articlesList.size() - 1);
        Log.e(this.getClass().getName(), " ArrayList Add Successfully");
    }


    public void setList(List<NewsModel.ArticlesEntity> articles) {
        this.articlesList.clear();
        this.articlesList = articles;
        notifyDataSetChanged();
        Log.e(this.getClass().getName(), " ArrayList Add Successfully");
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView time, title, details;
        ImageView newsImg;

        private NewsViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.news_time);
            title = view.findViewById(R.id.news_title);
            details = view.findViewById(R.id.news_details);
            newsImg = view.findViewById(R.id.image);
            view.findViewById(R.id.ripple).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(Utils.Json_title, articlesList.get(getAdapterPosition()).getTitle());
                    intent.putExtra(Utils.Json_fullNewUrl, articlesList.get(getAdapterPosition()).getUrl());
                    context.startActivity(intent);
                }
            });


        }



    }


}
