package com.example.collegescheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.collegescheduler.Activities.AssignmentActivity;
import com.example.collegescheduler.Activities.CourseActivity;
import com.example.collegescheduler.Activities.ExamActivity;

public class MainActivity extends AppCompatActivity {
    Button assignmentsButton;
    Button coursesButton;

    Button examsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignmentsButton = findViewById(R.id.assignmentsButton);
        coursesButton = findViewById(R.id.coursesButton);
        examsButton = findViewById(R.id.examsButton);
        assignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AssignmentActivity.class);
                startActivity(i);
            }
        });
        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CourseActivity.class);
                startActivity(i);
            }
        });
        examsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ExamActivity.class);
                startActivity(i);
            }
        });
    }
}