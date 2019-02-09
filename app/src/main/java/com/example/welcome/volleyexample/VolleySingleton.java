package com.example.welcome.volleyexample;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequetQueue;

    public static VolleySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }
    public VolleySingleton(Context context) {
        mRequetQueue = getRequestQueue(context);
    }

    public  RequestQueue getRequestQueue(Context context) {
        if(mRequetQueue == null){
            mRequetQueue = Volley.newRequestQueue(context);
        }
        return mRequetQueue;
    }
}
