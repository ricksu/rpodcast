
package com.podcast.rick.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.CancellationSignal;

import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;

import android.util.Log;

public class PodcastProvider extends ContentProvider {
    private PodcastDatabaseHelper db;

    private static final String AUTHORITY = "com.rick.podcast";

    private static final int INFOS = 10;
    private static final int INFO_ID = 20;

    private static final String BASE_PATH = "podinfo";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, INFOS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", INFO_ID);
    }

    @Override
    public boolean onCreate() {
        db = new PodcastDatabaseHelper(getContext());
        return false;
    }

    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        checkColumns(projection);

        queryBuilder.setTables(PodcastInfoTable.TABLE_PODCAST_INFO);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case INFOS:
                break;
            case INFO_ID:
                queryBuilder.appendWhere(PodcastInfoTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI:" + uri);
        }

        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }

    private void checkColumns(String[] projection) {

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case INFOS:
                id = sqlDB.insert(PodcastInfoTable.TABLE_PODCAST_INFO, null, values);
                Log.e("Rick", "insert called");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        int rowDeleted = 0;
        switch (uriType) {
            case INFOS:
                rowDeleted = sqlDB.delete(PodcastInfoTable.TABLE_PODCAST_INFO, selection, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowDeleted;
    }

    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = db.getWritableDatabase();
        int rowUpdated = 0;
        switch (uriType) {
            case INFOS:
                rowUpdated = sqlDB.update(PodcastInfoTable.TABLE_PODCAST_INFO, values, selection, selectionArgs);
                break;

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowUpdated;
    }
}