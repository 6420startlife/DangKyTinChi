package com.ptithcm.dangkytinchi.services;

import static com.ptithcm.dangkytinchi.utils.Credentials.BASE_URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ptithcm.dangkytinchi.interfaces.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));
    public static Retrofit retrofit = retrofitBuilder.build();
    public static ApiInterface api = retrofit.create(ApiInterface.class);

    public static ApiInterface getApi() {
        return api;
    }
}
