package com.example.on_tap_mob201.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.on_tap_mob201.Database.DbHelper;
import com.example.on_tap_mob201.Model.TinTuc;

import java.util.ArrayList;
import java.util.List;

public class TintucDAO {
        private SQLiteDatabase db;

        public TintucDAO(Context context){
            DbHelper dbHelper = new DbHelper(context);
            db = dbHelper.getWritableDatabase();
        }

        public long insert(TinTuc object){
            ContentValues values = new ContentValues();
            values.put("id",object.id);
            values.put("description",object.description);
            values.put("title",object.title);
            values.put("pubDate",object.pubDate);

            return db.insert("Tintuc",null,values);
        }

        public int update(TinTuc object){
            ContentValues values = new ContentValues();
            values.put("id",object.id);
            values.put("description",object.description);
            values.put("title",object.title);
            values.put("pubDate",object.pubDate);

            return db.update("Tintuc",values,"description=?",new String[]{String.valueOf(object.description)});
        }

        public int delete(String description){
            return db.delete("Tintuc","description=?",new String[]{String.valueOf(description)});
        }

        //////

        public List<TinTuc> getAllData(){
            String sql = "SELECT * FROM Tintuc";
            return getData(sql);
        }

        public TinTuc getItemById(String description){
            String sql = "SELECT * FROM Tintuc WHERE description=?";
            List<TinTuc> list = getData(sql,description);
            return list.get(0);
        }



        private List<TinTuc> getData(String sql,String...selectionArgs){
            List<TinTuc> list = new ArrayList<>();
            Cursor cursor = db.rawQuery(sql,selectionArgs);
            while (cursor.moveToNext()){
                TinTuc object = new TinTuc();
                object.id = (cursor.getString(cursor.getColumnIndex("id")));
                object.description = (cursor.getString(cursor.getColumnIndex("description")));
                object.pubDate = (cursor.getString(cursor.getColumnIndex("pubDate")));
                object.title = (cursor.getString(cursor.getColumnIndex("title")));
                list.add(object);
            }

            return list;
        }
}
