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

    private List<Pair<String, String>> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.view_task_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        LineAdapter adapter = new LineAdapter(this.taskList);
        recyclerView.setAdapter(adapter);

        TaskManager manager = new TaskManager();
        manager.list(this, taskList, adapter);
    }

    public void createTask(View view) {
        startActivity(new Intent(this, NewTaskActivity.class));
    }
}