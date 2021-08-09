package com.example.projectfoodrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DBRecipe extends SQLiteOpenHelper {
    public static final String Dbname="Recipe.db";
    public DBRecipe(Context context) {

        super(context,"Recipe.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table recipeList(filename text primary key,image blob)");

    }
    public boolean insert_recipe(String filename,byte[] image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue=new ContentValues();
        contentValue.put("filename",filename);
        contentValue.put("image",image);
        long result=db.insert("recipeList",null,contentValue);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getData()
    {
        SQLiteDatabase Db=this.getWritableDatabase();
        Cursor cursor=Db.rawQuery("Select filename from recipeList",null);
        return  cursor;

    }
    public void getDataDel(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Delete from recipeList where filename=?",new String[]{name});
        if(cursor.getCount()>0)
            System.out.println("deletion done");


    }
    public Bitmap imageSet(String file) {
        SQLiteDatabase db=this.getReadableDatabase();
        System.out.println(file);
        Cursor cursor= db.rawQuery("select image from recipeList where filename=?",new String[]{file});
        System.out.println("hi");
        if(cursor.moveToFirst()) {
            byte[] image = cursor.getBlob(0);
            for (int i = 0; i < image.length; i++)
                System.out.print(image[i]);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            cursor.close();
            return bitmap;
        }
        else
            return null;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists recipeList");

    }
}
