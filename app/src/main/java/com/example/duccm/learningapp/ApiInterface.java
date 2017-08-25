package com.example.duccm.learningapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by duccm on 8/25/17.
 */

public interface ApiInterface {
    @POST("user/login")
    Call<ResponseBody> login(@Body LoginCredentials login);
}
