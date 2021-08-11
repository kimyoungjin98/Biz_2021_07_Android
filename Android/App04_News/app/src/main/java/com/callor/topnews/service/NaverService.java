package com.callor.topnews.service;

import com.callor.topnews.model.NaverParent;

import retrofit2.Call;

public interface NaverService {

    public void getNews(String search);

}
