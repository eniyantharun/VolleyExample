package com.example.welcome.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private Button btnSendRequest;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private String url = "http://www.mocky.io/v2/5c5efba2320000bd0c40b454";
    private String TAG = MainActivity.class.getName();
    private String REQUESTTAG = "First String Request";
    private DiskBasedCache mCache;
    private Network mNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendRequest = findViewById(R.id.printButton);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestAndPrintResponse();                
            }
        });

    }

    private void sendRequestAndPrintResponse() {
        //mRequestQueue = Volley.newRequestQueue(this);

       /* mCache = new DiskBasedCache(getCacheDir(),4*1024*1024);
        mNetwork = new BasicNetwork(new HurlStack());

        mRequestQueue = new RequestQueue(mCache,mNetwork);

        mRequestQueue.start();
        */
       mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "RESPONSE :"+response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "ERROR : "+error.toString());
            }
        });
        stringRequest.setTag(REQUESTTAG);
        mRequestQueue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mRequestQueue!=null)
        {
            mRequestQueue.cancelAll(REQUESTTAG);
        }
    }
}
