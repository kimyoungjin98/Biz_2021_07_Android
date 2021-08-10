package com.young.lib.service;

import com.young.lib.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit을 사용하여 다른 network(server)에 접속하기 위한
 * 기본 설정 객체를 만들어서 배포하는 클래스
 */
public class RetrofitAPIClient {

    public static NaverRetrofit getApiClient(){
        NaverRetrofit naverRetrofit
                = getConnection().create(NaverRetrofit.class);

        return naverRetrofit;
    }

    /**
     * 접속 설정을 수행하고
     * 접속을 가능하게 하는
     * Connection instance를 만드는 method
     */
    private static Retrofit getConnection(){

        Retrofit retrofit
                = new Retrofit.Builder()
                .baseUrl(NaverAPI.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
