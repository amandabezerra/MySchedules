package com.amandabezerra.myschedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> schedulesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new LineAdapter(this.schedulesList);
        recyclerView.setAdapter(mAdapter);

        String urlListTasks = "http://prova.scytlbrasil.com:81/Api/tasks?userid=ce73f375568d4277b44f579e6ac1bc";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, urlListTasks, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);

                                schedulesList.add(obj.optString("Title"));
                                mAdapter.notifyDataSetChanged();
                                System.out.println(obj.optString("Title"));
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

        AppSingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }
}