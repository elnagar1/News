package com.example.news.Models;

public class HimitModel {

        private String id;
        private String publishedAt;
        private String urlToImage;
        private String url;
        private String description;
        private String title;

public  HimitModel(){

}

        public HimitModel(String id,String title, String description, String publishedAt, String urlToImage, String url) {
            this.title = title;
            this.description = description;
            this.publishedAt = publishedAt;
            this.urlToImage = urlToImage;
            this.url = url;
            this.id=id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    }


