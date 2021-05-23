package com.examples.concepts.utils;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Objects;

import static com.examples.concepts.utils.Constants.BASE_CONTENT_URI;
import static com.examples.concepts.utils.Constants.COLUMN_CATEGORY;
import static com.examples.concepts.utils.Constants.COLUMN_NAME;
import static com.examples.concepts.utils.Constants.COLUMN_PRICE;
import static com.examples.concepts.utils.Constants.CONTENT_AUTHORITY;
import static com.examples.concepts.utils.Constants.DATABASE_NAME;
import static com.examples.concepts.utils.Constants.TABLE_NAME;
import static com.examples.concepts.utils.Constants.URI_CODE;

public class ProductProvider extends ContentProvider {

    private static final UriMatcher uriMatcher;

    private SQLiteDatabase db;

    private static HashMap<String,String> values;

    private static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME +
    " ( id INTEGER PRIMARY KEY, " +
    COLUMN_NAME + " TEXT NOT NULL, " +
    COLUMN_CATEGORY + " TEXT NOT NULL, " +
    COLUMN_PRICE + " INTEGER NOT NULL);";

    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY ,"cte", URI_CODE);
        uriMatcher.addURI(CONTENT_AUTHORITY ,"cte/*", URI_CODE);
    }

    @Override
    public boolean onCreate() {
        ProductDBHelper productDBHelper = new ProductDBHelper(getContext());
        db = productDBHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        if (uriMatcher.match(uri) == URI_CODE) {
            qb.setProjectionMap(values);
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || Objects.equals(sortOrder, "")) {
            sortOrder = COLUMN_NAME;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if (uriMatcher.match(uri) == URI_CODE) {
            return "vnd.android.cursor.dir/cte";
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) throws SQLException {
        long rowID = db.insert(TABLE_NAME, "", values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(BASE_CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        if (uriMatcher.match(uri) == URI_CODE) {
            count = db.delete(TABLE_NAME, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count;
        if (uriMatcher.match(uri) == URI_CODE) {
            count = db.update(TABLE_NAME, values, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class ProductDBHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;

        public ProductDBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}