package com.young.lib.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit을 사용하여 API 접속을 할 때
 * 최종 end point, header 값, method type, parameter type 등을
 * 설정하는 인터페이스
 */
public interface NaverRetrofit {

    /**
     * method의 return type을 void 형식이 아닌
     * Call 클래스 type으로 설정
     */

    // 1. 가장 기본 타입, 아무일도 할수 없다
    public Call getBook();

    /**
     * 2. naver OpenAPI를 사용하기 위해서는
     * 반드시 header에 Client ID와 Secret 값을
     * 전달해 주어야 한다
     * header에 Client ID와 Secret 값을 전달하기 위하여
     * 가. method의 매개변수에 해당 속성을 지정해 준다
     * 나. @Header() annotation을 부착한다
     * 다. @Header() annotation에 Header의 이름을 지정한다
     *
     * 3. method의 end point와 요청 method type을 지정한다
     */

    @GET("book.json")
    public Call getBook(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display

    );

}
