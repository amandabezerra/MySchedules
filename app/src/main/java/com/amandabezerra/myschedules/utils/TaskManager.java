package com.amandabezerra.myschedules.utils;

import android.content.Context;
import android.support.v4.util.Pair;

import com.amandabezerra.myschedules.entity.Task;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private final static String userid = "ce73f375568d4277b44f579e6ac1bc";

    public void list(Context context, final List<Pair<String, String>> list, final LineAdapter adapter) {
        String urlListTasks = "http://prova.scytlbrasil.com:81/Api/tasks?userid=" + userid;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, urlListTasks, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                list.add(Pair.create(obj.optString("Title"), obj.optString("Id")));
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                // to do
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // to do
                    }
                });

        RequestQueueSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    public void create(Context context, final Task task) {
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

    public void update(Context context, final Task task, String taskId) {
        String urlUpdateTask = "http://prova.scytlbrasil.com:81/Api/tasks/EditTask?id=" + taskId + "&userid=" + userid;
        StringRequest postRequest = new StringRequest(Request.Method.POST, urlUpdateTask,
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

    public void detail(Context context, String taskId, final ServerCallback callback) {
        String urlDetailTask = "http://prova.scytlbrasil.com:81/Api/tasks?id=" + taskId + "&userid=" + userid;
//        String urlDetailTask = "http://prova.scytlbrasil.com/Api/tasks/GetTask?id=" + taskId + "&userid=" + userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlDetailTask, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Task task = new Task();
                        task.setTitle(response.optString("Title"));
                        task.setDescription(response.optString("Description"));
                        task.setCompleted(response.optString("Completed"));
                        task.setDeadline(response.optString("Deadline"));

                        callback.onSuccess(task);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // to do
                    }
                });

        RequestQueueSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void delete(Context context, String taskId) {
        String urlDeleteTask = "http://prova.scytlbrasil.com:81/Api/tasks/RemoveTask?id=" + taskId + "&userid=" + userid;
        StringRequest postRequest = new StringRequest(Request.Method.POST, urlDeleteTask,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // to do
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // to do
                    }
                });

        RequestQueueSingleton.getInstance(context).addToRequestQueue(postRequest);
    }
}
