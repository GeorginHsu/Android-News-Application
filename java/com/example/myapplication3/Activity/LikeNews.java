package com.example.myapplication3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication3.Adapter.DataAdapter;
import com.example.myapplication3.DataBase.MySimpleData;
import com.example.myapplication3.DataBase.NewsDatabaseDealer2;
import com.example.myapplication3.R;

import java.util.List;

public class LikeNews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<MySimpleData> simpleDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_news);

        recyclerView = findViewById(R.id.like_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backButton = findViewById(R.id.like_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loadLikeData(LikeNews.this);
        setupRecyclerView();

        Button refreshButton = findViewById(R.id.like_refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLikeData(LikeNews.this);
                setupRecyclerView();
            }
        });

    }

    private void loadLikeData(Context context) {
        NewsDatabaseDealer2 dealer = new NewsDatabaseDealer2(context);
        simpleDataList = dealer.getAllDataFromDatabase();
    }

    private void setupRecyclerView() {
        adapter = new DataAdapter(simpleDataList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}