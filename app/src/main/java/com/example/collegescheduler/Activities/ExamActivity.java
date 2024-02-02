package com.example.collegescheduler.Activities;
import java.util.Comparator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.collegescheduler.Adapters.ExamAdapter;
import com.example.collegescheduler.MainActivity;
import com.example.collegescheduler.TouchHelpers.ExamTouchHelper;
import com.example.collegescheduler.TouchHelpers.ToDoTouchHelper;
import com.example.collegescheduler.databinding.ActivityExamBinding;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerDatabase;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    Button homeButton;
    Button addButton;

    EditText examsName;
    EditText examsMonth;
    EditText examsDay;
    EditText examsYear;
    EditText examsTime;
    EditText examsLocation;


    private SchedulerDatabase schedulerDatabase;
    private ArrayList<Exam> examArrayList = new ArrayList<>();

    private ExamAdapter examAdapter;

    private ActivityExamBinding examBinding;

    SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);


        examBinding = DataBindingUtil.setContentView(this, R.layout.activity_exam);

        RecyclerView recyclerView = examBinding.examsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        schedulerDatabase = SchedulerDatabase.getInstance(this);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);

        schedulerViewModel.getAllExams().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                examArrayList.clear();
                for (Exam a : exams) {
                    examArrayList.add(a);
                }
                examAdapter.notifyDataSetChanged();
            }
        });

        Comparator<Exam> examComparator = new Comparator<Exam>() {
            @Override
            public int compare(Exam a, Exam b) {
                return a.getExamName().compareTo(b.getExamName());
            }
        };
        examArrayList.sort(examComparator);

        examAdapter = new ExamAdapter(examArrayList, ExamActivity.this);

        recyclerView.setAdapter(examAdapter);

        homeButton = findViewById(R.id.examsHomeButton);
        addButton = findViewById(R.id.examsAdd);
        examsName = findViewById(R.id.examsName);
        examsMonth = findViewById(R.id.examsMonth);
        examsDay = findViewById(R.id.examsDay);
        examsYear = findViewById(R.id.examsYear);
        examsTime = findViewById(R.id.examsTime);
        examsLocation = findViewById(R.id.examsLocation);

        //Listen to when Add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = examsName.getText().toString();
                int month = Integer.parseInt(examsMonth.getText().toString());
                int day = Integer.parseInt(examsDay.getText().toString());
                int year = Integer.parseInt(examsYear.getText().toString());
                String time = examsTime.getText().toString();
                String location = examsLocation.getText().toString();
                Exam newExam = new Exam(name, month, day, year, time, location);
                schedulerViewModel.addNewExam(newExam);
                examsName.setText("");
                examsMonth.setText("");
                examsDay.setText("");
                examsYear.setText("");
                examsTime.setText("");
                examsLocation.setText("");

            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExamActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ExamTouchHelper(examAdapter, schedulerViewModel));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

}