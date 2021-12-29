package com.theatifwaheed.exampractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView_showData;
    RecyclerAdapter recyclerAdapter;
    ArrayList<String> names_data;
    ArrayList<String> ids_data;
    SqliteDatabaseHelper sqliteDatabaseHelper;
    ArrayList<Bitmap> images_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_insert = findViewById(R.id.btn_insert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insert = new Intent(MainActivity.this, insertDataAct.class);
                startActivity(insert);
            }
        });

        names_data = new ArrayList<String>();
        ids_data = new ArrayList<String>();
        images_data = new ArrayList<Bitmap>();
        sqliteDatabaseHelper = new SqliteDatabaseHelper(MainActivity.this);
        fetch_data();
        fetch_image();
        recyclerView_showData = findViewById(R.id.rv_showData);
        recyclerView_showData.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(names_data, ids_data, images_data, this);
        recyclerView_showData.setAdapter(recyclerAdapter);
    }

    public void fetch_data(){
        Cursor res = sqliteDatabaseHelper.get_studentData();
        if(res.getCount()==0){
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()) {
            ids_data.add(res.getString(0));
            names_data.add(res.getString(1));
        }
    }
    public void fetch_image(){
        Cursor res = sqliteDatabaseHelper.get_studentImage();
        if(res.getCount()==0){
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;}
        while(res.moveToNext()) {
            byte[] imageInByte = res.getBlob(0);
            Bitmap studentImage = BitmapFactory.decodeByteArray(imageInByte, 0, imageInByte.length);
            images_data.add(studentImage);
        }
    }
}