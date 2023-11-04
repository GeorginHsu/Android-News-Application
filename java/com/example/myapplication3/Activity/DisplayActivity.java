package com.example.myapplication3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication3.Fragment.FatherFragment;
import com.example.myapplication3.DataDealer.GetURLString;
import com.example.myapplication3.R;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button backButton = findViewById(R.id.display_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String category = intent.getStringExtra("category");
        String startDate = intent.getStringExtra("startDate");
        String endDate = intent.getStringExtra("endDate");
        String url = GetURLString.GetString(category,word,startDate,endDate);


        FatherFragment fragment = new FatherFragment(url);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.display_fragment_container, fragment)
                .commit();
    }
}