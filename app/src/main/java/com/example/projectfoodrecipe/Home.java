package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
   Button recipe,add,del,nutri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recipe=(Button)findViewById(R.id.btn_recipe);
        add=(Button)findViewById(R.id.btn_addRecipe);
        del=(Button)findViewById(R.id.btn_delRecipe);
       // nutri=(Button)findViewById(R.id.btn_nutri);
        recipe.setOnClickListener(this);
        add.setOnClickListener(this);
        del.setOnClickListener(this);
       // nutri.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(recipe))
        {
            Intent it=new Intent(Home.this,RecipeList.class);
            startActivity(it);
        }
        if(v.equals(add))
        {
            Intent it=new Intent(Home.this,Add.class);
            startActivity(it);

        }
        if(v.equals(del))
        {
            Intent it=new Intent(Home.this,Delete.class);
            startActivity(it);
        }
    }
}