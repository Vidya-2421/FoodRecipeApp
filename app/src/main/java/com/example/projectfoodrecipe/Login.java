package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
   EditText username,password;
   Button login;
   DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username_login);
        password=(EditText)findViewById(R.id.password_login);
        login=(Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(this);
        db=new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(login))
        {
            String user=username.getText().toString();
            String pass=password.getText().toString();
            if(user.equals("")||pass.equals("")){
                Toast.makeText(Login.this,"Please enter all field",Toast.LENGTH_LONG).show();
            }
            else
            {
                Boolean check=db.checkPass(user,pass);
                if(check==true)
                {
                    Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Login.this,"Incorrect credentials",Toast.LENGTH_LONG).show();
            }


        }
    }
}