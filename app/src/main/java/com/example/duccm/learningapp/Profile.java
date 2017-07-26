package com.example.duccm.learningapp;

/**
 * Created by DucCM on 7/26/2017.
 */

public class Profile {
    private int id;
    private String avatar;
    private String name;
    private String email;
    private String phone;
    private String birthday;

    public Profile() {
    }

    public Profile(int id, String avatar, String name, String email, String phone, String birthday) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
