package com.example.myapplication3.DataDealer;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public class DatasGetter {
    private List<MyData> datas = new ArrayList<>();

    public synchronized void get(String url)
    {

        datas = URLReader.readDataFromURL(url);
        this.notifyAll();
    }

    public synchronized List<MyData> getResult()
    {

        if(datas.size()==0)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return this.datas;
    }


}
