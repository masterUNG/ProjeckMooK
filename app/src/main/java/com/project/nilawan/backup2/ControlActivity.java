package com.project.nilawan.backup2;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ControlActivity extends Activity {

    private Button bt_where;
    private Button bt_alarm;
    private Button bt_logout;
    private Button bt_bike;
    private Button bt_back;
    double motion;
    double motion2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);
        Intent i = getIntent();
        motion = i.getDoubleExtra("motion", 0);
        motion2 = i.getDoubleExtra("motion2", 0);
        bt_where = (Button) findViewById(R.id.button_where);
        bt_alarm = (Button) findViewById(R.id.button_alarm);
        bt_logout = (Button) findViewById(R.id.Blogout);
        bt_bike = (Button) findViewById(R.id.statusbike);
        bt_back = (Button) findViewById(R.id.Block);

        if (motion == 1){
            bt_bike.setBackgroundResource(R.drawable.checkbike);
        }

        if (motion == 0){
            bt_bike.setBackgroundResource(R.drawable.safe);
        }

        bt_alarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent al = new Intent(ControlActivity.this,ConnectStatusAl.class);
                finish();
                startActivity(al);
                onStop();
            }
        });

        bt_where.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent inte = new Intent(ControlActivity.this, MainActivity.class);
                finish();
                startActivity(inte);
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent inte = new Intent(ControlActivity.this, CheckNoti.class);
                finish();
                startActivity(inte);
            }
        });

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int ID =getIntent().getIntExtra("ID",8);

        nm.cancel(ID);

        bt_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent inte = new Intent(ControlActivity.this, ConnectLogin.class);
                startActivity(inte);
                Intent intent = new Intent(ControlActivity.this, MyService.class);  //service
                stopService(intent);
            }
        });
        //delat();
    }
/*
   private void delat() {
        android.os.Handler h = new android.os.Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                rerun();
            }
        }, 10000);
    }
    private void rerun() {
        Intent k = new Intent(getApplicationContext(), Connectmotion.class);

        startActivity(k);
    }
*/
}

