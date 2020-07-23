package com.example.news.utils;

import com.example.news.Models.NewsModel;

import java.util.List;

public interface ops {
   void get(List<NewsModel.ArticlesEntity> articlesEntities);

   void get2(List<NewsModel.ArticlesEntity> asList);
}
