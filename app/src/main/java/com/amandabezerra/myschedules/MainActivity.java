package com.amandabezerra.myschedules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amandabezerra.myschedules.utils.LineAdapter;
import com.amandabezerra.myschedules.utils.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pair<String, String>> schedulesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LineAdapter mAdapter = new LineAdapter(this.schedulesList);
        recyclerView.setAdapter(mAdapter);

        TaskManager manager = new TaskManager();
        manager.list(this, schedulesList, mAdapter);
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    public void editTask(View view) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra("taskId", "1");
        startActivity(intent);
    }
}