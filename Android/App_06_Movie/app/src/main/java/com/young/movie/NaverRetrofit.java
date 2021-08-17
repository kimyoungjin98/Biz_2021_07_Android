package com.young.movie;

import com.young.movie.model.NaverParent;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverRetrofit {

    public Call<NaverParent>
        getMovies(
                @Header("") String clientId,
                @Header("") String clientSecret,
                @Query("") String query,
                @Query("") int start,
                @Query("") int display
    );

}
