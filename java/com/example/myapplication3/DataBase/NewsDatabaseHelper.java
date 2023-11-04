package com.example.myapplication3.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewsDatabaseHelper extends SQLiteOpenHelper {


    public NewsDatabaseHelper(@Nullable Context context) {
        super(context, "news_history", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery =
                "CREATE TABLE " + Constants.HISTORY_TABLE_NAME + "(" +
                Constants.COLUMN_ID + " TEXT PRIMARY KEY, " +
                Constants.COLUMN_TITLE + " TEXT, " +
                Constants.COLUMN_PUBLISHTIME + " TEXT, " +
                Constants.COLUMN_PUBLISHER + " TEXT, " +
                Constants.COLUMN_CONTENT + " TEXT, " +
                "COLUMN_TIMESTAMP" + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
