package com.project.nilawan.backup2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SendLock extends Activity {

    String id = "1";
    String sys = "1";

    private static final String TAG = "SendLock.java";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_alarm);

    }

    public void onResume() {
        super.onResume();
        setup();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void setup() {

        id="1";
        sys = "1";
        new UploadTask().execute();

        //Intent inte = new Intent(sendAlarmOn.this, Notification.class);
        //startActivity(inte);
        Intent abc = new Intent (SendLock.this, CheckNoti.class);
        startActivity(abc);
    }

    private class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://tr.ddnsthailand.com/uploadsys.php");

            Log.v(TAG, "doIn;:");
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("id", "1"));
                nameValuePairs.add(new BasicNameValuePair("sys", "1"));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity resEntity = response.getEntity();
                Log.v(TAG,"Post::::" + response);
                if (resEntity != null) {
                    String responseStr = EntityUtils.toString(resEntity).trim();
                    Log.v(TAG,"respSTR:" + responseStr);
                    Log.v(TAG,"456");

                    return "1";
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.v(TAG,"Post");
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG,"Post");
            }


            return null;

        }

    }


}

