package com.podcast.rick.database;

import android.database.sqlite.SQLiteDatabase;

public class PodcastInfoTable {
    public static final String TABLE_PODCAST_INFO = "podcast_info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPION = "description";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_DOWNLOAD_STATE = "downloadstate";
    public static final String COLUMN_DOWNLOAD_PATH = "path";

    //image

    private static final String DATABASE_CREATE = "create table "
            + TABLE_PODCAST_INFO
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_UID + " integer, "
            + COLUMN_TITLE + " text, "        
            + COLUMN_DESCRIPION + " text, "        
            + COLUMN_DURATION + " integer, "
            + COLUMN_LINK + " text, "
            + COLUMN_DOWNLOAD_PATH + " text, "
            + COLUMN_DOWNLOAD_STATE + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}