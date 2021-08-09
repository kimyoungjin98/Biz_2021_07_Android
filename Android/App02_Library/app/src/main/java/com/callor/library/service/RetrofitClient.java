package com.callor.library.service;

import com.callor.library.config.Naver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // 1. OpenAPI와 연결할 Connection 객체를 생성하고
    private static Retrofit getInstance(){

        Gson gson = new GsonBuilder()
                .setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Naver.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    // 2. 미리 만들어둔 NaverRetrofitService interface와 연동하여
    // 데이터를 parsing하는 코드를 생성하여 return
    public static NaverRetrofitService getClient(){

        return getInstance().create(NaverRetrofitService.class);
    }

}
