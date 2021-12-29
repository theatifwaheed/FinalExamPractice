package com.theatifwaheed.exampractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class insertDataAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText_id;
    EditText editText_name;
    Button button_saveData, btn_show;
    ImageView imageView_showImage;
    Spinner spinner_selectImage;
    int pos;
    SqliteDatabaseHelper sqliteDatabaseHelper;
    String[] imagesName = { "Select Image", "Picture-1","Picture-2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(insertDataAct.this, MainActivity.class);
                startActivity(show);
            }
        });

        editText_id = findViewById(R.id.et_id);
        editText_name = findViewById(R.id.et_name);
        button_saveData = findViewById(R.id.btn_savedata);
        imageView_showImage = findViewById(R.id.iv_image);
        spinner_selectImage = findViewById(R.id.spin_selectImage);
        sqliteDatabaseHelper = new SqliteDatabaseHelper(this);
        ArrayAdapter imagesAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, imagesName);
        imagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_selectImage.setAdapter(imagesAdapter);
        spinner_selectImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(InsertDataActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                pos = position;
                if (position==0) {
                    Toast.makeText(insertDataAct.this, "Select Picture",
                            Toast.LENGTH_SHORT).show();
                } else if(position==1) {
                    imageView_showImage.setImageResource(R.drawable.p1);
                } else if(position==2) {
                    imageView_showImage.setImageResource(R.drawable.p2);
                } else Toast.makeText(insertDataAct.this, "Select Picture",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        button_saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText_id.getText().toString();
                String name = editText_name.getText().toString();
                if (pos == 0){
                    Toast.makeText(insertDataAct.this, "Select the Right Image",
                            Toast.LENGTH_SHORT).show();
                }
                else if (pos == 1){
                    Bitmap imageBitmap;
                    imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p1);
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte [] img = byteArray.toByteArray();
                    boolean insertFlag = sqliteDatabaseHelper.insertData(id,name,img);
                    if (insertFlag==true)
                        Toast.makeText(insertDataAct.this, "Saved",
                                Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(insertDataAct.this, "Not Saved",
                                Toast.LENGTH_SHORT).show();
                }
                else if (pos == 2){
                    Bitmap imageBitmap;
                    imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p2);
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte [] img = byteArray.toByteArray();
                    boolean insertFlag = sqliteDatabaseHelper.insertData(id,name,img);
                    if (insertFlag==true)Toast.makeText(insertDataAct.this, "Saved",
                            Toast.LENGTH_SHORT).show();
                    else Toast.makeText(insertDataAct.this, "Not Saved",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

//    public void ImageCoversion(){
//        Bitmap imageBitmap;
//        imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.p2);
//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
//        byte [] img = byteArray.toByteArray();
//        boolean insertFlag = sqliteDatabaseHelper.insertData(id,name,img);
//        if (insertFlag==true)Toast.makeText(InsertDataActivity.this, "Saved",
//                Toast.LENGTH_SHORT).show();
//        else Toast.makeText(InsertDataActivity.this, "Not Saved",
//                Toast.LENGTH_SHORT).show();
//    }
//    }
}