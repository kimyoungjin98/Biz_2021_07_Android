package com.young.movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.young.movie.adapter.NaverViewAdapter;
import com.young.movie.config.NaverAPI;
import com.young.movie.model.MovieDTO;
import com.young.movie.model.Parent;
import com.young.movie.service.NaverRetrofitClient;
import com.young.movie.service.NaverService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverServiceImplV1 implements NaverService {

    protected RecyclerView movieListView;

    public NaverServiceImplV1(RecyclerView movieListView) {
        this.movieListView = movieListView;
    }

    @Override
    public void getMovie(String search) {

        Call<Parent> naverCall = NaverRetrofitClient.getApiClient()
                .getMovie(
                        NaverAPI.CLIENT_ID,
                        NaverAPI.CLIENT_SECRET,
                        search,
                        10,
                        1
                );

        naverCall.enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                int resCode = response.code();
                if(resCode < 300){
                    Log.d("영화 조회", response.body().toString());
                    List<MovieDTO> movieList = response.body().items;
                    NaverViewAdapter viewAdapter
                            = new NaverViewAdapter(movieList);
                    movieListView.setAdapter(viewAdapter);
                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(movieListView.getContext());
                    movieListView.setLayoutManager(layoutManager);

                }
                else{
                    Log.d("오류발생", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {

            }
        });
    }
}
