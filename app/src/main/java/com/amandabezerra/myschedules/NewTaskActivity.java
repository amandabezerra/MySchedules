package com.amandabezerra.myschedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.amandabezerra.myschedules.entity.Task;
import com.amandabezerra.myschedules.utils.TaskManager;

public class NewTaskActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private CheckBox checkBoxYes;
    private CheckBox checkBoxYNo;
    private EditText editTextDeadlineDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        checkBoxYes = findViewById(R.id.check_box_yes);
        checkBoxYNo = findViewById(R.id.check_box_no);
        editTextDeadlineDate = findViewById(R.id.edit_text_deadline_date);

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
    }

    public void createTask(View view) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String completed = checkBoxYes.isChecked() ? "true" : "false";
        String deadline = editTextDeadlineDate.getText().toString();

        Task task = new Task(title, description, completed, deadline);
        TaskManager manager = new TaskManager();
        manager.create(this, task);

        startActivity(new Intent(this, MainActivity.class));
    }
}
