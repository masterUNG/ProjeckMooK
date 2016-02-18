package com.project.nilawan.backup2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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


public class Notification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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


            Intent it = new Intent(Notification.this, ControlActivity.class);
            it.putExtra("motion", motion);

            startActivity(it);

            

            /**
             String c = String.valueOf(a);
             String d = String.valueOf(b);
             textLatitude = (TextView) findViewById(R.id.textLatitude);
             textLatitude.setText(c);
             textLongitude = (TextView) findViewById(R.id.textLongitude);
             textLongitude.setText(d);
             //String sMemberID = ((TextView) myView.findViewById(R.id.ColMemberID)).getText().toString();
             // String sName = ((TextView) myView.findViewById(R.id.ColName)).getText().toString();
             // String sTel = ((TextView) myView.findViewById(R.id.ColTel)).getText().toString();
             */

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }


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