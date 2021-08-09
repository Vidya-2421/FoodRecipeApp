package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeList extends AppCompatActivity implements Serializable {
    DBRecipe db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        db=new DBRecipe(this);
        ListView listView=(ListView)findViewById(R.id.recipeList);
        ArrayList<String> list=new ArrayList<>();
        Cursor data=db.getData();
        System.out.println("entered");
        if(data.getCount()==0)
        {
            Toast.makeText(RecipeList.this,"db empty",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                String item1=data.getString(0);
                String i=item1.substring(0,item1.indexOf('.'));
                list.add(i);
            }
            ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(RecipeList.this,Recipe.class);
                    String item=list.get(position);
                    item=item+".txt";
                    intent.putExtra("Filename",item);
                    startActivity(intent);
            }
            });


    }
    }


}