package com.antoinegourtay.mob_e16_android;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by antoinegourtay on 18/12/2017.
 */

public class CryptoPlaceApplication extends Application {

        private RequestQueue requestQueue;

        @Override
        public void onCreate() {
            super.onCreate();
            requestQueue = Volley.newRequestQueue(this);
        }

        public RequestQueue getRequestQueue() {
            return requestQueue;
        }
}
