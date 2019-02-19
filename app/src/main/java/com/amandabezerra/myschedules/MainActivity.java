package com.amandabezerra.myschedules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> strings = new ArrayList<>();
        strings.add("Schedule nº01");
        strings.add("Schedule nº02");
        strings.add("Schedule nº03");
        strings.add("Schedule nº04");
        strings.add("Schedule nº05");
        strings.add("Schedule nº06");

        mAdapter = new LineAdapter(strings);
        recyclerView.setAdapter(mAdapter);
    }
}