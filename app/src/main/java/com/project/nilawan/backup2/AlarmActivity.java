package com.project.nilawan.backup2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmActivity extends Activity {
    private TextView text1, text2;
    private Button statusbike;
    private Button statusalarm;
    private Button Bback2;
    private Button bt_on;
    private Button bt_off;
    double motion;
    double alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Intent i = getIntent();
        alarm = i.getDoubleExtra("alarm", 0);

        //Intent j = getIntent();
        //alarm = j.getDoubleExtra("alarm", 0);
        if (alarm == 1) {
            TextView text1 = (TextView) findViewById(R.id.textView);
            text1.setText(String.format("%s", "ON"));
        }
        if (alarm == 0) {
            TextView text1 = (TextView) findViewById(R.id.textView);
            text1.setText(String.format("%s", "OFF"));
        }
/*
        statusbike = (Button) findViewById(R.id.button_green);
        if (motion == 1) {
            statusbike.setBackgroundResource(R.drawable.bikered);

        }
        if (motion == 0) {
            statusbike.setBackgroundResource(R.drawable.bikegreen);

        }
*/
        statusalarm = (Button) findViewById(R.id.button_status);

        if (alarm == 1) {
            statusalarm.setBackgroundResource(R.drawable.alred);

        }
        if (alarm == 0) {
            statusalarm.setBackgroundResource(R.drawable.algreen);

        }

        Bback2 = (Button) findViewById(R.id.Bback2);
        Bback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, Connectmotion.class);  //Notification
                startActivity(intent);
            }
        });

        bt_on = (Button) findViewById(R.id.Balarmon);

        bt_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                statusalarm.setBackgroundResource(R.drawable.alred);

                Intent inte = new Intent(AlarmActivity.this, sendAlarmOn.class);
                startActivity(inte);

            }
        });


        bt_off = (Button) findViewById(R.id.Balarmoff);

        bt_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                statusalarm.setBackgroundResource(R.drawable.algreen);
                Intent inte = new Intent(AlarmActivity.this, sendAlarmOff.class);
                startActivity(inte);
            }
        });

        //delat();

        //finish();
        //startActivity(getIntent());
    }

    /*

    private void delat() {
        android.os.Handler h = new android.os.Handler();
        h.postDelayed(new Runnable()
        {
            public void run() {
                rerun();
            }
        }, 10000);
    }
    private void rerun() {
        Intent k = new Intent(getApplicationContext(), Notification.class);

        startActivity(k);
    }
    */

}

