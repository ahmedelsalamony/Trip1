package com.example.itimobiletrack.trip1.loginregister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itimobiletrack.trip1.R;

public class RegisterActivity extends AppCompatActivity {
    EditText Name, email, password, verifyPass;
    Button signUp;
    Intent regToLog;
    /*Declaration of variables that will be saved on the shared preference*/
    static SharedPreferences ptrPrefs;
    SharedPreferences.Editor editor;
    String spName;
    String spEmail;
    String spPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        Name = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText6);
        verifyPass = (EditText) findViewById(R.id.editText7);
        signUp = (Button) findViewById(R.id.button3);
        ptrPrefs = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);

        /*Shared Preference Declaration*/

        editor = ptrPrefs.edit();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Name.getText().toString().equals("")){
                    Name.setError("This Field is required");
                }else if(!(email.getText().toString().contains("@"))){
                    email.setError("This Email is not valid");
                }else if(!(password.getText().toString().equals(verifyPass.getText().toString()))){
                    password.setError("Password Mismatch");
                    password.setText("");
                    verifyPass.setText("");
                    Toast.makeText(getApplicationContext(),"Please Re-Enter Password",Toast.LENGTH_SHORT).show();
                }else{
                    spName = Name.getText().toString();
                    spEmail = email.getText().toString();
                    spPassword = password.getText().toString();
                    editor.putString("User Name",spName).commit();
                    editor.putString("Email",spEmail).commit();
                    editor.putString("Password",spPassword).commit();
                    regToLog = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(regToLog);
                    finish();
                }

            }
        });
    }
}
