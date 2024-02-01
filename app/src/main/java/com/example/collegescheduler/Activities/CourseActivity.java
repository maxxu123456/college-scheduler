package com.example.collegescheduler.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.collegescheduler.Adapters.AssignmentAdapter;
import com.example.collegescheduler.Adapters.CourseAdapter;
import com.example.collegescheduler.MainActivity;
import com.example.collegescheduler.databinding.ActivityAssignmentBinding;
import com.example.collegescheduler.databinding.ActivityCourseBinding;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerDatabase;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    Button homeButton;
    Button addButton;

    EditText coursesName;
    EditText coursesTime;
    EditText coursesInstructor;


    private SchedulerDatabase schedulerDatabase;
    private ArrayList<Course> courseArrayList = new ArrayList<>();

    private CourseAdapter courseAdapter;

    private ActivityCourseBinding courseBinding;

    SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        courseBinding = DataBindingUtil.setContentView(this, R.layout.activity_course);

        RecyclerView recyclerView = courseBinding.coursesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        schedulerDatabase = SchedulerDatabase.getInstance(this);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);

        schedulerViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseArrayList.clear();
                for (Course a : courses) {
                    courseArrayList.add(a);
                }
                courseAdapter.notifyDataSetChanged();
            }
        });

        courseAdapter = new CourseAdapter(courseArrayList);

        recyclerView.setAdapter(courseAdapter);

        homeButton = findViewById(R.id.coursesHomeButton);
        addButton = findViewById(R.id.coursesAdd);
        coursesName = findViewById(R.id.coursesName);
        coursesTime = findViewById(R.id.coursesTime);
        coursesInstructor = findViewById(R.id.coursesInstructor);

        //Listen to when Add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = coursesName.getText().toString();
                String time = coursesTime.getText().toString();
                String instructor = coursesInstructor.getText().toString();
                Course newCourse = new Course(name, time, instructor);
                schedulerViewModel.addNewCourse(newCourse);
                coursesName.setText("");
                coursesTime.setText("");
                coursesInstructor.setText("");

            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}