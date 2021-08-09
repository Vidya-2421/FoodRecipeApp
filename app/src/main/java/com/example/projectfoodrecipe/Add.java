package com.example.projectfoodrecipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

public class Add extends AppCompatActivity implements View.OnClickListener {
    Button save, ch;
    EditText name, steps;
    ImageView imageView;
    String filecontent = "";
    String filename,filecreate;
    String filepath = "MyFileDir1";
    Uri imageUri;
    String file;

    DBRecipe db;
    private static final int PICK_IMAGE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(this);
        ch = (Button) findViewById(R.id.btn_choose);
        ch.setOnClickListener(this);
        steps = (EditText) findViewById(R.id.recipecontent);
        steps.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (steps.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });
        imageView = (ImageView) findViewById(R.id.recipe_image);
        //imageSet=(ImageView)findViewById(R.id.recipe_image2) ;
        name = (EditText) findViewById(R.id.filename);
        db=new DBRecipe(this);
        if (!isExternalStorageAvailabeForRW()) {
            save.setEnabled(false);
        }
    }

    private boolean isExternalStorageAvailabeForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if (extStorageState.equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(save)) {
            filename = name.getText().toString();
            file=filename+".txt";
            filecontent = steps.getText().toString().trim();
            System.out.println(filecontent);
            System.out.println(filename);
            if (!filecontent.equals("")) {
                File myfile = new File(getExternalFilesDir(filepath),file);
                FileOutputStream fos = null;
                try {

                         fos = new FileOutputStream(myfile);
                         fos.write(filecontent.getBytes());


                } catch (Exception e) {
                    e.printStackTrace();
                }
                insert_it();
                steps.setText("");
                Toast.makeText(Add.this, "Recipe save", Toast.LENGTH_LONG).show();

            }
        }
        else if (v.equals(ch)) {
                openGallery();
            }

        }

    private void insert_it() {

        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebytes=byteArrayOutputStream.toByteArray();


  try {

      boolean result=db.insert_recipe(file,imagebytes);
      System.out.println("entered");

      if (result == true)
          Toast.makeText(Add.this, "inserted into database", Toast.LENGTH_LONG).show();
      else
          Toast.makeText(Add.this, "not inserted into database", Toast.LENGTH_LONG).show();
  }catch(Exception e){}
    }


    private void openGallery() {
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(resultCode==RESULT_OK&&requestCode==PICK_IMAGE){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}






