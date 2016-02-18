package com.project.nilawan.backup2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Lock extends Activity {

    double sys;

    private Button bt_lock;
    private Button bt_unlock;
    private Button bt_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);

        Intent i = getIntent();
        sys = i.getDoubleExtra("sys",sys);

        if (sys == 1) {
            TextView text1 = (TextView) findViewById(R.id.notistatus);
            text1.setText(String.format("%s", "OFF"));
        }
        if (sys == 0) {
            TextView text1 = (TextView) findViewById(R.id.notistatus);
            text1.setText(String.format("%s", "ON"));
        }



        bt_lock = (Button) findViewById(R.id.Block);
        bt_unlock = (Button) findViewById(R.id.Bunlock);
        bt_menu = (Button) findViewById(R.id.Bmenu);

        bt_lock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt_lock.setBackgroundResource(R.drawable.lockblue);
                Intent intent = new Intent(Lock.this, SendLock.class);
                startActivity(intent);
            }
        });

        bt_unlock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt_unlock.setBackgroundResource(R.drawable.unlockblue);
                Intent inte = new Intent(Lock.this, SendUnlock.class);
                startActivity(inte);
            }
        });

        bt_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt_menu.setBackgroundResource(R.drawable.menublue);
                Intent inte = new Intent(Lock.this, Connectmotion.class);
//                Intent inte = new Intent(Lock.this, TestConnectedMotion.class);
                startActivity(inte);
            }
        });
    }
}
