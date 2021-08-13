package com.young.movie.service;

import com.young.movie.model.Parent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverRetrofit {

    @GET("movie.json")
    public Call<Parent> getMovie(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("display") int display,
            @Query("start") int start
    );

}
