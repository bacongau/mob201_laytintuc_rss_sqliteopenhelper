package com.example.on_tap_mob201.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "DB docbao.db";
    static final int dbVersion = 2;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTintuc = "CREATE TABLE Tintuc(" +
                "id TEXT , " +
                "description TEXT PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "pubDate TEXT NOT NULL)";
        db.execSQL(createTableTintuc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableTintuc = "DROP TABLE IF EXISTS Tintuc";
        db.execSQL(dropTableTintuc);

        onCreate(db);
    }
}
