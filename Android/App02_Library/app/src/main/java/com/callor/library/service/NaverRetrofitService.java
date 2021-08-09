package com.callor.library.service;

import com.callor.library.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit을 사용할때 URL 정의
 * 만약 URL이 https://naver.com/aaa/bbb/ccc/book.json 형태로 되어있을때
 * URL의 끝의 end point인 book.json은 @GET("book.json")으로 설정한다
 * RetroService 에서 instance를 만들때 사용한 코드
 *      Retrofit.Builder().baseUrl() 부분에 매개변수로
 *      https://naver.com/aaa/bbb/ccc/ 를 전달해 준다
 *
 * 실제 Connection 객체를 만들때
 *  baseUrl과 @GET()에 설정된 EndPoint를 합성하여
 *  https://naver.com/aaa/bbb/ccc/book.json 와 같은 URL을 생성하게 된다
 */
public interface NaverRetrofitService {

    // Retrofit을 사용하여 GET method로 OpenAPI에 접속하라
    @GET("book.json")
    Call<NaverParent> getNaverBook(

            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("display") int display,
            @Query("start") int start

    );
    // 최종 생성되는 queryString
    // book.json?query=자바&display=10&start=1

}
