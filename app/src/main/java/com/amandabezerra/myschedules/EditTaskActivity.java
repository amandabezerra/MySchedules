package com.amandabezerra.myschedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.amandabezerra.myschedules.entity.Task;
import com.amandabezerra.myschedules.utils.ServerCallback;
import com.amandabezerra.myschedules.utils.TaskManager;

public class EditTaskActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private CheckBox checkBoxYes;
    private CheckBox checkBoxYNo;
    private EditText editTextDeadlineDate;

    private String taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskId = getIntent().getExtras().getString("TASK_ID");

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

        TaskManager manager = new TaskManager();
        manager.detail(this, taskId, new ServerCallback() {
                    @Override
                    public void onSuccess(Task result) {
                        editTextTitle.setText(result.getTitle());

                        if (result.getDescription().equals("null")) {
                            editTextDescription.setText("");
                        } else {
                            editTextDescription.setText(result.getDescription());
                        }

                        if (result.getCompleted().equals("true")) {
                            checkBoxYes.setChecked(true);
                        }
                        if (result.getCompleted().equals("false")) {
                            checkBoxYNo.setChecked(true);
                        }

                        String deadline = result.getDeadline();
                        if (deadline.equals("null")) {
                            deadline = "";
                        } else {
                            StringBuilder deadlineFormated = new StringBuilder();
                            deadlineFormated.append(deadline.substring(5, 7));
                            deadlineFormated.append("/");
                            deadlineFormated.append(deadline.substring(8, 10));
                            deadlineFormated.append("/");
                            deadlineFormated.append(deadline.substring(0, 4));

                            deadline = deadlineFormated.toString();
                        }

                        editTextDeadlineDate.setText(deadline);
                    }
                }
        );
    }

    public void updateTask(View view) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String completed = checkBoxYes.isChecked() ? "true" : "false";
        String deadline = editTextDeadlineDate.getText().toString();

        Task task = new Task(title, description, completed, deadline);

        TaskManager manager = new TaskManager();
        manager.update(this, task, taskId);

        startActivity(new Intent(this, MainActivity.class));
    }

    public void deleteTask(View view) {
        Intent intent = new Intent(this, DeleteTaskActivity.class);

        if (getIntent().getExtras() != null) {
            intent.putExtra("TASK_ID", getIntent().getExtras().getString("TASK_ID"));
        }

        startActivity(intent);
    }
}
