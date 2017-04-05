package com.example.itimobiletrack.trip1;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    Ringtone mAlarmSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        mAlarmSound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        TextView btnDone = (TextView)findViewById(R.id.DoneButton);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mAlarmSound.isPlaying())
                    mAlarmSound.play();
            }
        }, 500);
    }

    @Override
    public void onBackPressed() {
        mAlarmSound.stop();
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            return false;
        }
        return true;
    }

    public static class AlarmReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {

            if (intent.getBooleanExtra("Test", false)){
                Log.i("Test", "Found");
            } else{
                Log.i("Test", "Nada");
            }
            Intent dialogIntent = new Intent(context, DialogActivity.class);
            dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(dialogIntent);
        }


    }
}
