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
import com.example.myapplication3.DataBase.NewsDatabaseDealer;
import com.example.myapplication3.R;

import java.util.List;

public class HistoryNews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<MySimpleData> simpleDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_news);



        recyclerView = findViewById(R.id.history_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //点击返回按钮
        Button backButton = findViewById(R.id.history_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loadHistoryData(HistoryNews.this);
        setupRecyclerView();

        //点击清空历史记录按钮
        Button clearButton = findViewById(R.id.history_clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDatabaseDealer dealer=new NewsDatabaseDealer(HistoryNews.this);
                dealer.clear();
                loadHistoryData(HistoryNews.this);
                setupRecyclerView();
            }
        });

    }

    private void loadHistoryData(Context context) {
        NewsDatabaseDealer dealer = new NewsDatabaseDealer(context);
        simpleDataList = dealer.getAllDataFromDatabase();
    }



    private void setupRecyclerView() {
        adapter = new DataAdapter(simpleDataList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


}