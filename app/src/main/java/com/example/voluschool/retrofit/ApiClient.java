package com.example.voluschool.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://voluschool.pagekite.me/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(new OkHttpClient().newBuilder()
//                            .connectTimeout(60, TimeUnit.SECONDS)
//                            .readTimeout(60, TimeUnit.SECONDS)
//                            .writeTimeout(60, TimeUnit.SECONDS)
//                            .build())
                    .build();
        }
        return retrofit;
    }
}
