package com.project.nilawan.backup2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Connectmotion extends Activity {

    double motion;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);

        //Connected Database
        connectedDatabase();


        //ReceiveSys
        receiveSys();


        //Try Post Delay
       // tryPostDelay();

        //Thread Sleep1000
//        try {
//            myThreadSleep();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    } // onCreate

    private void receiveSys() {

        {

            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    return null;
                }
            };

        }//AsyncTask

    }   // receiveSys

    private void myThreadSleep() throws InterruptedException {

       for (int i=1;i<10;i++) {
           Thread.sleep(2000);
           connectedDatabase();
       }

    }


    private void tryPostDelay() {

        for (int i=1;i<50;i++) {
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    connectedDatabase();
                }
            }, 2000 * i);
        }



    }   // tryPostDelay

    private void connectedDatabase() {



        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 16) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        String url = "http://tr.ddnsthailand.com/motion.php";

        try {

            JSONArray data = new JSONArray(getJSONUrl(url));

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);
                map = new HashMap<>();
                map = new HashMap<String, String>();
                map.put("id", c.getString("id"));
                map.put("motion", c.getString("motion"));
                MyArrList.add(map);

            }


            final AlertDialog.Builder viewDetail = new AlertDialog.Builder(this);
            // OnClick Item
            String smotion = MyArrList.get(0).get("motion");
            double motion = Double.parseDouble(smotion);

            String[] resultSmotion = {"ไม่มีการเคลื่อนที่", "มีการเคลื่อนที่"};
            int intIndex = Integer.parseInt(smotion);
            Log.d("18Feb", "result ==> " + resultSmotion[intIndex]);


            Intent it = new Intent(Connectmotion.this, NotificationAlarm.class);
            it.putExtra("motion" , motion);

            startActivity(it);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   // Try Case

    }   // connectedDatabase


    public String getJSONUrl(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}

