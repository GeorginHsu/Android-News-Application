package com.example.myapplication3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication3.R;

public class SearchActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button yesButton = findViewById(R.id.yes_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDisplayActivity();
            }
        });

    }

    private void sendDataToDisplayActivity() {
        EditText editText1 = findViewById(R.id.editTextWord);
        EditText editText2 = findViewById(R.id.editTextCategory);
        EditText editText3 = findViewById(R.id.editTextStartDate);
        EditText editText4 = findViewById(R.id.editTextEndDate);

        String word = "";
        String category = "";
        String startDate = "";
        String endDate = "";

        if(editText1.getText()!=null)
        {
            word = editText1.getText().toString();
        }
        if (editText2.getText()!=null)
        {
            category = editText2.getText().toString();
        }
        if(editText3.getText()!=null)
        {
            startDate = editText3.getText().toString();
        }
        if(editText4.getText()!=null)
        {
            endDate = editText4.getText().toString();
        }

        if((!endDate.equals(""))&&(!startDate.equals(""))&&(endDate.compareTo(startDate)<0))
        {
            Toast.makeText(this, "时间输入有误哦{{(>_<)}}", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, DisplayActivity.class);
            intent.putExtra("word", word);
            intent.putExtra("category", category);
            intent.putExtra("startDate", startDate);
            intent.putExtra("endDate", endDate);
            startActivity(intent);
        }
    }




}