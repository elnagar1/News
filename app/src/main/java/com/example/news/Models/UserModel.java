package com.example.news.Models;

public class UserModel {


    String name;
    String photo;
    String email;
    String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserModel() {
    }

    public UserModel(String name, String photo, String email, String uid) {
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.uid = uid;
    }
}
