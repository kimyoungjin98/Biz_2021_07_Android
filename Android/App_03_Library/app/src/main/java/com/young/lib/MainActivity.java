package com.young.lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.young.lib.service.NaverBookService;
import com.young.lib.service.impl.NaverBookServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.book_list_view);

        NaverBookService naverBookService =
                    new NaverBookServiceImplV1(recyclerView);
        naverBookService.getBooks("자바");


    }


}