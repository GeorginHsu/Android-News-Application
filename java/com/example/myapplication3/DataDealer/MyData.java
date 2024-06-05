package com.example.myapplication3.DataDealer;


import android.util.Log;

public class MyData {
    public String[] image;

    public String lotsOfImage;
    public String publishTime;
    public String language;
    public String video;
    public String title;
    public String content;
    public String newsID;
    public String publisher;

    public MyData(String lotsOfImage, String publishTime, String language,String video,String title,String content,String newsID,String publisher)
    {
        this.lotsOfImage=lotsOfImage;
        this.image = lotsOfImage.replaceAll("\\[|\\]|,"," ").split("\\s+");
        Log.i("initIMAGE",image.length+"");
        this.publishTime=publishTime;
        this.language=language;
        this.video=video;
        this.title=title;
        this.content=content;
        this.newsID=newsID;
        this.publisher=publisher;
    }

}



