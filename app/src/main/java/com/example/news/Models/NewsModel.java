package com.example.news.Models;

import java.util.List;

public class NewsModel {

    private List<ArticlesEntity> articles;
    private int totalResults;
    private String status;

    public List<ArticlesEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesEntity> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ArticlesEntity {

        private String publishedAt;
        private String urlToImage;
        private String url;
        private String description;
        private String title;
        private SourceEntity source;
        public ArticlesEntity(){

        }
        public ArticlesEntity(String title, String description, String publishedAt, String urlToImage, String url){
            this.title =title;
            this.description=description;
            this.publishedAt =publishedAt;
            this.urlToImage =urlToImage;
            this.url= url;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public SourceEntity getSource() {
            return source;
        }

        public void setSource(SourceEntity source) {
            this.source = source;
        }
    }

    public static class SourceEntity {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}