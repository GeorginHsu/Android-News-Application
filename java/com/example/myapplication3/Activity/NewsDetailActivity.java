package com.example.myapplication3.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.myapplication3.DataBase.NewsDatabaseDealer2;
import com.example.myapplication3.R;


public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();  // 获取启动该活动的 Intent
        String newsContent = intent.getStringExtra("newsContent");  // 获取传递的新闻内容数据
        String newsTitle = intent.getStringExtra("newsTitle");
        String newsPublishTime = intent.getStringExtra("newsPublishTime");
        String newsVideo = intent.getStringExtra("newsVideo");
        String newsPublisher = intent.getStringExtra("newsPublisher");
        String newsImage = intent.getStringExtra("newsImage");
        String newsID = intent.getStringExtra("newsID");


        TextView newsTitleView = findViewById(R.id.newsdetail_title);
        TextView newsPublishTimeView = findViewById(R.id.newsdetail_publish_time);
        TextView newsPublisherView = findViewById(R.id.newsdetail_publisher);
        Button setLikeButton = findViewById(R.id.set_like_button);
        NewsDatabaseDealer2 dealer = new NewsDatabaseDealer2(NewsDetailActivity.this);
        if(dealer.isNewsExists(newsID)){
            setLikeButton.setText("取消收藏");
        } else {
            setLikeButton.setText("收藏");
        }
        newsTitleView.setText(newsTitle);
        newsPublishTimeView.setText("发布时间: "+newsPublishTime);
        newsPublisherView.setText("来源: "+newsPublisher);
        TextView newsContentView = findViewById(R.id.newsdetail_content);
        newsContentView.setText(newsContent);

        VideoView videoView = findViewById(R.id.newsdetail_video);


        ImageView imageView = findViewById(R.id.newsdetail_image);
        if (newsImage!=null) {
            if (newsImage.equals("") == false) {
                // 使用 Glide 加载和显示图像
                Glide.with(this)
                        .load(newsImage)
                        .into(imageView);
            }
        }


        //显示视频
        if (newsVideo!=null) {
            if(newsVideo.equals("")==false)
            {
                Log.d("videourl",newsVideo);

                String videoUrl = newsVideo.replaceFirst("http","https");
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.setVideoPath(videoUrl);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        videoView.start();
                    }
                });
            } else videoView.setVisibility(VideoView.GONE);
        } else videoView.setVisibility(VideoView.GONE);

        setLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ad",newsID);
                NewsDatabaseDealer2 dealer = new NewsDatabaseDealer2(NewsDetailActivity.this);


                if(!dealer.isNewsExists(newsID)){
                    dealer.insert(newsID,newsTitle,newsPublishTime,newsPublisher,newsContent);
                    setLikeButton.setText("取消收藏");

                } else {
                    dealer.delete(newsID);
                    setLikeButton.setText("收藏");
                }
            }
        });




    }
}