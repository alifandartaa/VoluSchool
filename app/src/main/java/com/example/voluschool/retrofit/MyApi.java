package com.example.voluschool.retrofit;

import com.example.voluschool.model.Login;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.LoginResponse;
import com.example.voluschool.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyApi {

    @POST("post/register")
    Call<RegisterResponse> register(@Body User user);

    @POST("post/login")
    Call<LoginResponse> login(@Body Login login);
}
