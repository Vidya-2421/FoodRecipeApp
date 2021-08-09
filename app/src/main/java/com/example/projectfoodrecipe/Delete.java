package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Delete extends AppCompatActivity {
    ListView del;
    DBRecipe db;
    String filepath="MyFileDir1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        del = (ListView) findViewById(R.id.listDel);
        db = new DBRecipe(this);
        ListView listView = (ListView) findViewById(R.id.listDel);
        ArrayList<String> list1 = new ArrayList<>();
        Cursor data = db.getData();
        System.out.println("entered");
        if (data.getCount() == 0) {
            Toast.makeText(Delete.this, "Error", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                String item1=data.getString(0);
                String i=item1.substring(0,item1.indexOf('.'));
                System.out.println(list1.add(i));

            }
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list1);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int i=1;
                    String item = list1.get(position);
                    System.out.println(item);
                    item=item+".txt";
                    File file = new File(getExternalFilesDir(filepath),item);
                    System.out.println("done");
                    if (file.exists()) {
                        System.out.println("enter just now");
                        Boolean res= file.delete();
                        if(res) {
                            Toast.makeText(Delete.this, "File Deleted successfully", Toast.LENGTH_LONG).show();
                            db.getDataDel(item);
                            if (i == 1) {
                                Toast.makeText(Delete.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Delete.this, Home.class);
                                startActivity(intent);
                            }
                        }

                    }




                }

            });
        }


    }

}