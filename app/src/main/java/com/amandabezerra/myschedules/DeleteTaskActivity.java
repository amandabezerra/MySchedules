package com.amandabezerra.myschedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amandabezerra.myschedules.utils.TaskManager;

public class DeleteTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_task);
    }

    public void confirm(View view) {
        TaskManager manager = new TaskManager();

        if (getIntent().getExtras() != null) {
            manager.delete(this, getIntent().getExtras().getString("TASK_ID"));
        }

        startActivity(new Intent(this, MainActivity.class));
    }

    public void cancel(View view) {
        finish();
    }
}
