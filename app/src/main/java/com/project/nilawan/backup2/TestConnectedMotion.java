package com.project.nilawan.backup2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestConnectedMotion extends AppCompatActivity {

    private Double sy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_connected_motion);

        Receivesys();


    }   // Test Method

    private void Receivesys() {

        {   // Start Asynctask


            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... voids) {
                    OkHttpClient okHttpClient = new OkHttpClient();

                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url("http://tr.ddnsthailand.com/checknoti.php").build();

                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        if (response.isSuccessful()) {
                            return response.body().string();
                        } else {
                            return "Not Success - code : " + response.code();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "Error - " + e.getMessage();
                    }
                }

                @Override
                protected void onPostExecute(String string) {
                    super.onPostExecute(string);


                    try {

                        JSONArray data = new JSONArray(string);
                        final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
                        HashMap<String, String> map;

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject c = data.getJSONObject(i);
                            map = new HashMap<>();
                            map = new HashMap<String, String>();
                            map.put("id", c.getString("id"));
                            map.put("sys", c.getString("sys"));
                            MyArrList.add(map);

                        }
                        String ssys = MyArrList.get(0).get("sys");

                        double sys = Double.parseDouble(ssys);

                        sy = sys;


                        condition();


                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }

                private void condition() {


                    /**station1*/
                    if (sy == 1)  {
                        TextView text1 = (TextView) findViewById(R.id.txtTestConnected);
                        text1.setText("OFF");
                    }

                    else if (sy == 0)  {
                        TextView text1 = (TextView) findViewById(R.id.txtTestConnected);
                        text1.setText("ON");
                    }


                    Loop();

                }


            }.execute();

        }   // AsyncTask
    }   // Receivesys

    private void Loop() {

        android.os.Handler h = new android.os.Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                Receivesys();
            }
        }, 20000);

    }   // Loop
}   // Main Class
