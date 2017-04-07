package com.podcast.rick.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PodcastDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "podcast.db";
    private static final int DATABASE_VERSION = 1;

    public PodcastDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PodcastInfoTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        PodcastInfoTable.onUpgrade(db, oldVersion, newVersion);
    }
}

