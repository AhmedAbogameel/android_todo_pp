package com.example.todoapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class AppSQLiteHelper extends SQLiteOpenHelper {

    public static final String dbName = "Tasks";
    public static final int dbVersion = 1;

    public AppSQLiteHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion); // If not Exist with same Name and Version >> Create Empty DB File
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creat tables SQL
        // insert   update  delete   select
        db.execSQL("CREATE TABLE TASKS (" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTask(SQLiteDatabase db, String name){
        String sql = "insert into TASKS(NAME) values (?)";
        String[] values = {name};
        db.execSQL(sql, values);
    }

    public void deleteTask(SQLiteDatabase db, int id){
        db.delete("TASKS","_id = " + id, null);
//        String sql = "insert into TASKS(NAME) values (?)";
//        db.execSQL(sql);
    }

    public Map<Integer, String> getTasks(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("select * from TASKS",null);
        Map<Integer, String> tasks = new HashMap<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                tasks.put(id ,name);
                cursor.moveToNext();
            }
        }
        return tasks;
    }

}
