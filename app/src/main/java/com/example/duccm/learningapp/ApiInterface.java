package com.example.duccm.learningapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by duccm on 8/25/17.
 */

public interface ApiInterface {
    @POST("user/login")
    Call<ResponseBody> login(@Body LoginCredentials login);

    @Headers({
            "Content-Type: application/json",
            "authorization: JWT eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9"
    })
    @GET("timeline")
    Call<List<Post>> getTimeline();
}
