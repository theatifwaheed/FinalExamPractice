package com.theatifwaheed.exampractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "studentDetails.db";
    private static int DATABASE_VERSION = 1;
    private static String TABLENAME = "studentinfo";
    private String query_createTable = "create table " + TABLENAME + " (" +
            "std_id TEXT primary key,"+
            "std_name TEXT, std_image BLOB)";
    private String query_selectTable = "select * from " + TABLENAME;
    Context mainContext;

    public SqliteDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mainContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(query_createTable);
            Toast.makeText(mainContext, "Table Created", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String s_id, String s_name, byte [] s_image){
        Toast.makeText(mainContext, "Id: "+s_id +" Name: "+s_name, Toast.LENGTH_SHORT).show();
        Toast.makeText(mainContext, "Image: " + s_image, Toast.LENGTH_SHORT).show();
        SQLiteDatabase sql_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("std_id", s_id);
        contentValues.put("std_name", s_name);
        contentValues.put("std_image", s_image);
        long checkInsert = sql_db.insert(TABLENAME, null, contentValues);
        Toast.makeText(mainContext, "Insert Rank: " + checkInsert, Toast.LENGTH_SHORT).show();
        if (checkInsert == -1) return false;
        else return true;
    }
    public Cursor get_studentData(){
        Cursor cursor = null;
        try{
            SQLiteDatabase sql_db = this.getWritableDatabase();
            cursor = sql_db.rawQuery("select std_id,std_name from " + TABLENAME, null);
        }catch (Exception e){
            Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }
    public Cursor get_studentImage(){
        Cursor cursor = null;
        try{
            SQLiteDatabase sql_db = this.getWritableDatabase();
            cursor = sql_db.rawQuery("select std_image from " + TABLENAME, null);
        }catch (Exception e){
            Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    public String getStudentName_byId(String s_id){
        SQLiteDatabase sql_db = this.getWritableDatabase();
        Cursor cursor = sql_db.rawQuery(query_selectTable + " where std_id = ?",
                new String []{s_id});
        cursor.moveToFirst();
        return cursor.getString(1);
    }
    public Bitmap get_studentImage_byId(String s_id){
        SQLiteDatabase sql_db = this.getWritableDatabase();
        Cursor cursor = sql_db.rawQuery(query_selectTable + " where std_id = ?",
                new String[]{s_id});
        cursor.moveToFirst();
        byte [] imageInByte = cursor.getBlob(2);
        Bitmap studentImage = BitmapFactory.decodeByteArray(imageInByte,0, imageInByte.length);
        return studentImage;
    }

}
