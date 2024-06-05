package com.example.myapplication3.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewsDatabaseDealer2 {
    private NewsDatabaseHelper2 mHelper;
    private String tableName;


    public NewsDatabaseDealer2(Context context) {
        mHelper = new NewsDatabaseHelper2(context);
    }

    public void insert(String newsId, String newsTitle, String newsPublishTime, String newsPublisher, String newsContent)
    {
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "insert into " + "news_database" + "(COLUMN_ID,COLUMN_TITLE,COLUMN_PUBLISHTIME,COLUMN_PUBLISHER,COLUMN_CONTENT) values(?,?,?,?,?)";
//        db.execSQL(sql,new Object[]{newsId,newsTitle,newsPublishTime,newsPublisher,newsContent});
        ContentValues values = new ContentValues();
        values.put("COLUMN_ID",newsId);
        values.put("COLUMN_TITLE",newsTitle);
        values.put("COLUMN_PUBLISHTIME",newsPublishTime);
        values.put("COLUMN_PUBLISHER",newsPublisher);
        values.put("COLUMN_CONTENT",newsContent);
        db.insert(Constants.LIKE_TABLE_NAME,null,values);
        db.close();
    }

    public void delete(String newsId)
    {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        if (db != null) {
            String whereClause = Constants.COLUMN_ID + " = ?";
            String[] whereArgs = {newsId};
            db.delete(Constants.LIKE_TABLE_NAME, whereClause, whereArgs);
        }
        db.close();
    }


    //将数据库内的信息提取处理
    public List<MySimpleData> getAllDataFromDatabase() {
        List<MySimpleData> dataList = new ArrayList<>();

        SQLiteDatabase db = mHelper.getReadableDatabase();
        String query = "SELECT * FROM "+Constants.LIKE_TABLE_NAME+" ORDER BY COLUMN_TIMESTAMP DESC";
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("COLUMN_ID");
            int titleIndex = cursor.getColumnIndex("COLUMN_TITLE");
            int publishTimeIndex = cursor.getColumnIndex("COLUMN_PUBLISHTIME");
            int publisherIndex = cursor.getColumnIndex("COLUMN_PUBLISHER");
            int contentIndex = cursor.getColumnIndex("COLUMN_CONTENT");
            String id = cursor.getString(idIndex);
            String title = cursor.getString(titleIndex);
            String publishTime = cursor.getString(publishTimeIndex);
            String publisher = cursor.getString(publisherIndex);
            String conctent = cursor.getString(contentIndex);
            MySimpleData item = new MySimpleData(id,title,publishTime,publisher,conctent);
            dataList.add(item);
        }

        cursor.close();
        db.close();

        return dataList;
    }


    //判断新闻是否已经存在于数据库中
    public boolean isNewsExists(String newsId) {
        boolean exists;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        if(db!=null) {
            String[] projection = {Constants.COLUMN_ID};
            String selection = Constants.COLUMN_ID + " =?";
            String[] selectionArgs = {newsId};
            Cursor cursor = db.query(
                    Constants.LIKE_TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            exists = (cursor.getCount() > 0);
            cursor.close();
        } else{
            exists = false;
        }
        db.close();
        return exists;
    }


}
