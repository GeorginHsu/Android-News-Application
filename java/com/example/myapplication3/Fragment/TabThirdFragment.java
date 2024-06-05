package com.example.myapplication3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication3.Activity.HistoryNews;
import com.example.myapplication3.Activity.LikeNews;
import com.example.myapplication3.R;

public class TabThirdFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // 在这里加载和配置Fragment的布局
            View view = inflater.inflate(R.layout.page_three_fragment , container, false);
            // 执行其他的布局配置和初始化操作

            Button btnHistory = view.findViewById(R.id.history_button);
            btnHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 启动 HistoryActivity
                    Intent intent = new Intent(getActivity(), HistoryNews.class);
                    startActivity(intent);
                }
            });

            Button btnLike = view.findViewById(R.id.like_button);
            btnLike.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getActivity(), LikeNews.class);
                    startActivity(intent);
                }
            });


            return view;
        }
    }

