package com.example.news.Models;

public class SeModel {
    int link  ;
    String name;
    String category;
    public SeModel(int link, String name, String category) {
        this.link = link;
        this.name = name;
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getLLink(){
        return link;
    }
}
