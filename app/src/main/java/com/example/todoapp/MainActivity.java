package com.example.todoapp;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Map<Integer, String> tasks;
    private AppSQLiteHelper appSQLiteHelper;
    private SQLiteDatabase db;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTasks();
        initRecyclerView();
    }

    private void initTasks(){
        appSQLiteHelper = new AppSQLiteHelper(this);
        db = appSQLiteHelper.getWritableDatabase();
//        appSQLiteHelper.insertTask(db ,"Ahmed");
        tasks = appSQLiteHelper.getTasks(db);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this, tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onFABClicked(View view) {
        appSQLiteHelper.insertTask(db, "Task");
        adapter.notifyDataSetChanged();
    }
}