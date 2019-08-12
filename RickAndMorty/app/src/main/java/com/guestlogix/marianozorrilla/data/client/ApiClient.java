package com.guestlogix.marianozorrilla.data.client;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.guestlogix.marianozorrilla.util.Constants;

public class ApiClient implements Constants {

    private RequestQueue queue;

    public ApiClient(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void initClient(API api, int page, Response.Listener<String> listener,
                           Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                BASE_URL + api.getUrl() + page,
                listener, error);
        queue.add(stringRequest);
    }
}
