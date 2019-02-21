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
import com.amandabezerra.myschedules.service.TaskCreator;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

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

        final Button btn = findViewById(R.id.buttonCreate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                String completed = checkBoxYes.getText().toString();
                String deadline = editTextDeadlineDate.getText().toString();

                Task task = new Task(title, description, completed, deadline);
                TaskCreator creator = new TaskCreator(context);
                creator.create(task);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
