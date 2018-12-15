package com.gunfermehmetcan.jsonvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private String URL = "http://mehmetcangunfer.xyz/";
    private String ALL_DATA_URL = URL + "all_data.php";
    private String SELECTED_DATA_URL = URL + "selected-data.php";


    private String DENEME_URL = URL + "deneme.php";
    RequestQueue queue;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(getApplicationContext());
        fillData();
        //getAllData();
        //getSelectedData(2);

    }



    private void fillData(){




        StringRequest postRequest = new StringRequest(
               // Request.Method.POST, // POST tipinde istekte bulunduk
               // Request.Method.GET, // GET tipinde istekte bulunduk
                Request.Method.PUT,  //GET tipinde istekte bulunduk
                DENEME_URL,
                new Response.Listener<String>() {




                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;
                            jsonObject = jsonArray.getJSONObject(0);
                            Name=jsonObject.getString("ad");
                            Log.d("response",response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }







                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("RESPONSE",error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("ad","serif"); //POST tipinde gönderilen key ve value'ları
                //;params.put("yas","24");     //POST tipinde gönderilen key ve value'ları
                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }









/*
--------------------------------------------------------------------------------------------------
    private void getAllData()
    {
       StringRequest sr = new StringRequest(Request.Method.POST, ALL_DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJson(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });

       AppController.getInstance().addToRequestQueue(sr);
    }


-----------------------------------------------------------------------------------
    private void getSelectedData(final int id)
    {
        StringRequest sr = new StringRequest(Request.Method.POST, SELECTED_DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJson(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + id);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(sr);
    }

----------------------------------------------------------------------------------
    private void parseJson(String json)
    {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
            JSONObject jsonObject;
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                Log.d(TAG, "Rank : " + jsonObject.getInt("rank"));
                Log.d(TAG, "Name : " + jsonObject.getString("name"));
                Log.d(TAG, "Year : " + jsonObject.getInt("year"));
                Log.d(TAG, "Rating : " + jsonObject.getDouble("rating"));
                Log.d(TAG, "---------");
            }
        } catch (JSONException e) {
            e.printStackTrace();

    }

}
--------------------------------------------------------------------------------------------------
*/

}
