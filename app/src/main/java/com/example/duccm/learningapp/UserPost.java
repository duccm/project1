package com.example.duccm.learningapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duccm on 8/31/17.
 */

public class UserPost {
    @SerializedName("id")
    private Integer id;

    UserPost() {
    }

    UserPost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
