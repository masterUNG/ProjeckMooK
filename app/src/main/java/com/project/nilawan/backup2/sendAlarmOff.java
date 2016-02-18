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

public class sendAlarmOff extends Activity {

    String alarm = "0";
    String id = "1";
    private static final String TAG = "sendAlarmOff.java";

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
        alarm = "0";
        new UploadTask().execute();

        Intent inte = new Intent(sendAlarmOff.this, Notification.class);
        startActivity(inte);
        Intent abc = new Intent (sendAlarmOff.this, ConnectStatusAl.class);
        startActivity(abc);
    }

    private class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://tr.ddnsthailand.com/uploadalarm.php");

            Log.v(TAG, "doIn;:");
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("id", "1"));
                nameValuePairs.add(new BasicNameValuePair("alarm", "0"));

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


/*
        private String inputStreamToString(InputStream is) throws IOException {
            String line = "";
            StringBuilder total = new StringBuilder();

            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }

            // Return full string
            return total.toString();
        }
*/       
    }

}