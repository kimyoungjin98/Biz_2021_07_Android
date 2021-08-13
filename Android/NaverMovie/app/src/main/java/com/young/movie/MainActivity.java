package com.young.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.young.movie.databinding.ActivityMainBinding;
import com.young.movie.service.NaverService;
import com.young.movie.service.impl.NaverServiceImplV1;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        NaverService naverService
                = new NaverServiceImplV1(mainBinding.movieListView);

        naverService.getMovie("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
//
//        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setQueryHint("영화 검색");


        getMenuInflater().inflate(R.menu.search_toolbar, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.movie_search_bar).getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("영화 제목 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                NaverService naverService
                        = new NaverServiceImplV1(mainBinding.movieListView);
                naverService.getMovie(query.trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}