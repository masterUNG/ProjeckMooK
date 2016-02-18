package com.project.nilawan.backup2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Clone extends Activity {
    private TextView text1, text2;
    private Button status;
    private Button ala;
    private Button Bback2;
    private Button bt_on;
    private Button bt_off;
    double motion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Intent i = getIntent();
        motion = i.getDoubleExtra("motion", 0);

        //Intent j = getIntent();
        //motion = j.getDoubleExtra("alarm", 0);

        TextView text1 = (TextView) findViewById(R.id.textView);
        text1.setText(String.format("%.0f", motion));

        status = (Button) findViewById(R.id.button_green);
        if (motion == 1) {
            status.setBackgroundResource(R.drawable.bikered);

        }
        if (motion == 0) {
            status.setBackgroundResource(R.drawable.bikegreen);

        }

        ala = (Button) findViewById(R.id.button_status);
/*
        if (motion == 1) {
            status.setBackgroundResource(R.drawable.alred);

        }
        if (motion == 0) {
            status.setBackgroundResource(R.drawable.algreen);

        }
*/
        Bback2 = (Button) findViewById(R.id.Bback2);
        Bback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clone.this, ControlActivity.class);
                startActivity(intent);
            }
        });

        bt_on = (Button) findViewById(R.id.Balarmon);

        bt_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ala.setBackgroundResource(R.drawable.alred);
                //Intent inten = new Intent(AlarmActivity.this, sendAlarmOn.class);
                //startActivity(inten);
            }
        });


        bt_off = (Button) findViewById(R.id.Balarmoff);

        bt_off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ala.setBackgroundResource(R.drawable.algreen);
                //Intent inte = new Intent(AlarmActivity.this, sendAlarmOff.class);
                //startActivity(inte);
            }
        });


    }



}

