package com.example.itimobiletrack.trip1.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.trip1.HomeActivity;
import com.example.itimobiletrack.trip1.R;


public class LoginActivity extends AppCompatActivity {
    Button log;
    Button register;
    EditText email;
    EditText passWord;
    TextView pass;
    TextView emailAdd;
    Intent home;
    static SharedPreferences ptrPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ptrPrefs = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.button2);
        email = (EditText) findViewById(R.id.editText);
        passWord = (EditText) findViewById(R.id.editText2);
        pass = (TextView) findViewById(R.id.textView2);
        emailAdd = (TextView) findViewById(R.id.textView);


        if (!(ptrPrefs.getString("Password", "").equals(""))) {

            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (email.getText().toString().equals("")) {
                        email.setError("This Field is required");
                    } else if (passWord.getText().toString().equals("")) {
                        passWord.setError("This Field is required");
                    } else {
                        if ((email.getText().toString().equals(ptrPrefs.getString("Email", ""))) && (passWord.getText().toString().equals(ptrPrefs.getString("Password", "")))) {
                            home = new Intent(LoginActivity.this, HomeActivity.class);
                            ptrPrefs.edit().putBoolean("isLoggedIn", true).commit();
                            startActivity(home);
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Email or Password Mismatch", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Not signed in please Register", Toast.LENGTH_SHORT).show();

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(logToRegister);
            }
        });

    }
}
