package com.callor.topnews.service;

import com.callor.topnews.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NverRetrofitClient {

    // static type으로 Connection(instance)를 만드는 method

    private static Retrofit getInstance(){

        return new Retrofit.Builder()
                .baseUrl(NaverAPI.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    // 생성된 Connection 객체를 사용할 수 있도록 제공하는 method
    public static NaverRetrofit getApiClient(){

        return getInstance().create(NaverRetrofit.class);
    }
}
