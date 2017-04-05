package com.example.itimobiletrack.trip1.loginregister;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.itimobiletrack.trip1.HomeActivity;
import com.example.itimobiletrack.trip1.R;
import com.example.itimobiletrack.trip1.transformation.CircleTransform;
import com.squareup.picasso.Picasso;

public class Splash extends AppCompatActivity {

    private static int SplashTimeOut = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView im=(ImageView)findViewById(R.id.imgsplash);

        Picasso.with(this).load(R.drawable.logo).resize(320,320)
                .transform(new CircleTransform()).into(im);

        final HomeActivity home = new HomeActivity();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(home.reg == false) {
                    Intent intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(Splash.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SplashTimeOut);
    }



}
