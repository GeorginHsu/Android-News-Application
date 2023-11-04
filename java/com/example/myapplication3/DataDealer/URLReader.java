package com.example.myapplication3.DataDealer;

import android.util.Log;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class URLReader {
    public static List<MyData> readDataFromURL(String urlString) {
        List<MyData> myDataList=new ArrayList<>();
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 设置连接超时时间为 5 秒


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String jsonResponse = response.toString();

                Gson gson = new Gson();

                Map map=gson.fromJson(jsonResponse,Map.class);
                List<Map> datas = (List<Map>) map.get("data");
                for(Map data:datas){
                    String mPublishTime = (String)data.get("publishTime");
                    String mLanguage = (String)data.get("language");
                    String mImage = (String)data.get("image");
                    String mVideo = (String)data.get("video");
                    String mTitle = (String)data.get("title");
                    String mContent = (String)data.get("content");
                    String mNewsID = (String)data.get("newsID");
                    String mPublisher = (String)data.get("publisher");
                    myDataList.add(new MyData(mImage,mPublishTime,mLanguage,mVideo,mTitle,mContent,mNewsID,mPublisher));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return myDataList;
    }
}
