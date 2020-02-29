package com.example.voluschool.retrofit;

import com.example.voluschool.model.Login;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.LoginResponse;
import com.example.voluschool.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyApi {

    @POST("user/register")
    Call<RegisterResponse> register(@Body User user);

    @POST("user/login")
    Call<LoginResponse> login(@Body Login login);
}
