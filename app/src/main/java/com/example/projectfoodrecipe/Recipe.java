package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

public class Recipe extends AppCompatActivity {
    DBRecipe db;

    TextView file1;
    TextView procedure1;
    ImageView dbimg;
    String myData="";
    String filepath="MyFileDir1";
    String line="";
    String dbfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent i=getIntent();
        String name = i.getStringExtra("Filename");
        String f=name.substring(0,name.indexOf('.'));
       // System.out.println(dbfile);
        String filepath="MyFileDir1";
        db=new DBRecipe(this);
        file1=(TextView)findViewById(R.id.file);
        //Environment.getExternalStorageDirectory(),
        procedure1=(TextView)findViewById(R.id.procedures);
        procedure1.setMovementMethod(new ScrollingMovementMethod());
        dbimg=(ImageView)findViewById(R.id.dbimage);
        file1.append(f);
        imageSet1(name);
        readfile(name,filepath);
    }

    private void imageSet1(String file) {
        Bitmap bitmap=db.imageSet(file);
        if(bitmap!=null)
           dbimg.setImageBitmap(bitmap);
        else
            System.out.println("Failed");

}
    private void readfile(String name,String filepath) {
        if (isExternalStorageStateReadable()) {
           FileReader fr=null;
           File file=new File(getExternalFilesDir(filepath),name);
           StringBuilder stringBuilder=new StringBuilder();
            try {

                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                line = br.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = br.readLine();
                }
            }catch (IOException e) {
                    e.printStackTrace();
                }
            procedure1.setText(stringBuilder.toString());



           }

            //procedure1.setText(myData);
            //File in=new File(getExternalFilesDir(filepath),name);
        }


    private boolean isExternalStorageStateReadable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())||
        Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState()))
        {
            return true;
        }
        else
            return false;
    }

}