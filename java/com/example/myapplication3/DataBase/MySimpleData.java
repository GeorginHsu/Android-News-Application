package com.example.myapplication3.DataBase;

public class MySimpleData {
    public String newsID;
    public String title;
    public String publishTime;
    public String publisher;
    public String content;



    public MySimpleData(String newsID,String title,String publishTime,String publisher,String content)
    {

        this.publishTime=publishTime;
        this.title=title;
        this.content=content;
        this.newsID=newsID;
        this.publisher=publisher;
    }

}
