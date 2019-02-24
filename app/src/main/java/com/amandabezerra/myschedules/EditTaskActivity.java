package com.amandabezerra.myschedules;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.amandabezerra.myschedules.entity.Task;
import com.amandabezerra.myschedules.utils.ServerCallback;
import com.amandabezerra.myschedules.utils.TaskManager;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        final Context context = this;

        final EditText editTextTitle = findViewById(R.id.editTextTitle);
        final EditText editTextDescription = findViewById(R.id.editTextDescription);
        final CheckBox checkBoxYes = findViewById(R.id.checkBoxYes);
        final CheckBox checkBoxYNo = findViewById(R.id.checkBoxNo);
        final EditText editTextDeadlineDate = findViewById(R.id.editTextDeadlineDate);

        checkBoxYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxYes.isChecked()) {
                    checkBoxYNo.setChecked(false);
                }

            }
        });
        checkBoxYNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxYNo.isChecked()) {
                    checkBoxYes.setChecked(false);
                }

            }
        });

        final Button btn = findViewById(R.id.buttonUpdate);

        final String taskId = getIntent().getExtras().getString("taskId");
        TaskManager manager = new TaskManager();
        manager.detail(this, taskId, new ServerCallback() {
            @Override
                public void onSuccess(Task result) {
                    editTextTitle.setText(result.getTitle());
                    System.out.println(result.getTitle());
                    editTextDescription.setText(result.getDescription());
                    if (result.getCompleted().equals("true")) checkBoxYes.setChecked(true);
                    if (result.getCompleted().equals("false")) checkBoxYNo.setChecked(true);
                    editTextDeadlineDate.setText(result.getDeadline().replace("T00:00:00", ""));
                }
            }
        );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                String completed = checkBoxYes.isChecked() ? "true" : "false";
                String deadline = editTextDeadlineDate.getText().toString();

                Task task = new Task(title, description, completed, deadline);
                TaskManager manager = new TaskManager();

                manager.update(context, task, taskId);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
