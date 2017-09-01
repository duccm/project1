package com.example.duccm.learningapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DucCM on 7/25/2017.
 */

public class Post implements Serializable {
    private String image;

    @SerializedName("comment_cnt")
    private Integer commentCnt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private Integer id;

    @SerializedName("like_cnt")
    private Integer likeCnt;

    @SerializedName("share_cnt")
    private Integer shareCnt;

    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_post")
    private UserPost userPost;

    public Post() {
    }

    public Post(UserPost userPost, String status, String image, String createdAt) {
        this.userPost = userPost;
        this.status = status;
        this.image = image;
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserPost getUserPost() {
        return userPost;
    }

    public void setUserPost(UserPost userPost) {
        this.userPost = userPost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(Integer commentCnt) {
        this.commentCnt = commentCnt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Integer likeCnt) {
        this.likeCnt = likeCnt;
    }

    public Integer getShareCnt() {
        return shareCnt;
    }

    public void setShareCnt(Integer shareCnt) {
        this.shareCnt = shareCnt;
    }
}
