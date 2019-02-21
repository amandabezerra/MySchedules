package com.amandabezerra.myschedules.service;

import android.content.Context;

import com.amandabezerra.myschedules.entity.Task;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TaskCreator {

    private final static String userid = "ce73f375568d4277b44f579e6ac1bc";
    private Context context;

    public TaskCreator(Context context) {
        this.context = context;
    }

    public void create(final Task task) {
        String urlCreateTask = "http://prova.scytlbrasil.com:81/Api/tasks/PostTask/authentication?userid=" + userid;
        StringRequest postRequest = new StringRequest(Request.Method.POST, urlCreateTask,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // to do
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // to do
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("title", task.getTitle());
                params.put("description", task.getDescription());
                params.put("completed", task.getCompleted());
                params.put("deadline", task.getDeadline());
                params.put("userid", userid);

                return params;
            }
        };

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }
}
