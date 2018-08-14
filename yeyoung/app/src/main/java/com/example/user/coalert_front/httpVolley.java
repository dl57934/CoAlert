package com.example.user.coalert_front;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.webkit.MimeTypeMap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;


class VolleyService {
        String url = "http://172.30.1.9a:3000/test";
        void volleyFunctions(Context context, String userID, String Cosmetic) throws JSONException {
            RequestQueue queue = Volley.newRequestQueue(context);
            JSONObject sendData = new JSONObject();
            sendData.put("userID",userID);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
                    sendData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            queue.add(req);
        }
}