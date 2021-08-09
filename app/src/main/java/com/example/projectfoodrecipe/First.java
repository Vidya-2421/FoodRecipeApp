package com.example.projectfoodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class First extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass, repass;
    Button register, login_main;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        user = (EditText) findViewById(R.id.user_main);
        pass = (EditText) findViewById(R.id.pass_main);
        repass = (EditText) findViewById(R.id.repass_main);
        register=(Button)findViewById(R.id.register) ;
        login_main=(Button)findViewById(R.id.btnlogin_main);
        register.setOnClickListener(this);
        login_main.setOnClickListener(this);
        db = new DBHelper(this);

    }
    public void onClick(View v) {
        if (v.equals(register)) {
            String usertxt = user.getText().toString();
            String password = pass.getText().toString();
            String repassword = repass.getText().toString();
            if (usertxt.equals("") || password.equals("") || repassword.equals(""))
                Toast.makeText(First.this, "Please Enter data in all fields", Toast.LENGTH_LONG).show();
            else {
                if (password.equals(repassword)) {
                    Boolean check = db.check(usertxt);
                    if (check == false) {
                        Boolean insert = db.insert(usertxt, password);
                        if (insert == true) {
                            Toast.makeText(First.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(First.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(First.this, "Username exists enter a different user name", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(First.this, "Password not matching", Toast.LENGTH_LONG).show();
            }

        }
        if (v.equals(login_main)) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }

    }
