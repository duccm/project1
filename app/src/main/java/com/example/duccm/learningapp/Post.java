package com.example.duccm.learningapp;

/**
 * Created by DucCM on 7/25/2017.
 */

public class Post {
    private int user_id;
    private String status;
    private String image;
    private String time;

    public Post() {
    }

    public Post(int user_id, String status, String image, String time) {
        this.user_id = user_id;
        this.status = status;
        this.image = image;
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
