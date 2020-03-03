package com.example.voluschool.retrofit;

import com.example.voluschool.model.Login;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.DonationPostResponse;
import com.example.voluschool.responses.LoginResponse;
import com.example.voluschool.responses.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MyApi {

    @POST("user/register")
    Call<RegisterResponse> register(@Body User user);

    @POST("user/login")
    Call<LoginResponse> login(@Body Login login);

    @Multipart
    @POST("donasi/sekoldon")
    Call<DonationPostResponse> donate(
            @Part("sekolah") RequestBody schoolName,
            @Part("butuh") RequestBody donationCost,
            @Part("deskripsi") RequestBody story,
            @Part MultipartBody.Part schoolImage
    );
}
