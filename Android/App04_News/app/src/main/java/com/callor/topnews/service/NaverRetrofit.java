package com.callor.topnews.service;

import com.callor.topnews.model.NaverNewsDTO;
import com.callor.topnews.model.NaverParent;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit을 사용하여 API 조회를 할때
 * 필요한 코드를 자동으로 생성하기 위한 interface
 * Retrofit의 interface는 기본적으로 return type이 정해져 있다
 *
 * return type Call<DTO>
 * DTO(VO) 클래스를 Generic으로 갖는 Call 클래스 return type으로
 * method를 선언해야 한다
 *
 * @GET("endpoint") 형식의 annotation을 설정해 둔다
 *
 * 그
 * 만
 * 자
 * 자
 * !!!
 */
public interface NaverRetrofit {

    @GET("news.json")
    public Call<NaverParent> getNews(

            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("display") int display,
            @Query("start") int start

    );



}
