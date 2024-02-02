package com.example.collegescheduler.Activities;

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

import com.example.collegescheduler.Adapters.AssignmentAdapter;
import com.example.collegescheduler.MainActivity;
import com.example.collegescheduler.TouchHelpers.AssignmentsTouchHelper;
import com.example.collegescheduler.TouchHelpers.ExamTouchHelper;
import com.example.collegescheduler.databinding.ActivityAssignmentBinding;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerDatabase;
import com.example.collegescheduler.SchedulerViewModel;

import java.util.ArrayList;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {
    //By Default, Assignments is sorted by Date
    private enum AssignmentSort {
        DATE,
        COURSE
    }

    Button homeButton;
    Button addButton;

    Button dateSort;

    Button courseSort;
    EditText assignmentsName;
    EditText assignmentsDueMonth;
    EditText assignmentsDueDay;
    EditText assignmentsDueYear;
    EditText assignmentsClass;

    AssignmentSort sort = AssignmentSort.DATE;

    private SchedulerDatabase schedulerDatabase;
    private ArrayList<Assignment> assignmentArrayList = new ArrayList<>();

    private AssignmentAdapter assignmentAdapter;

    private ActivityAssignmentBinding assignmentBinding;

    SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        assignmentBinding = DataBindingUtil.setContentView(this, R.layout.activity_assignment);

        RecyclerView recyclerView = assignmentBinding.assignmentsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        schedulerDatabase = SchedulerDatabase.getInstance(this);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);

        schedulerViewModel.getAllAssignments().observe(this, new Observer<List<Assignment>>() {
            @Override
            public void onChanged(List<Assignment> assignments) {
                assignmentArrayList.clear();
                for (Assignment a : assignments) {
                    assignmentArrayList.add(a);
                }
                if (sort == AssignmentSort.DATE) {
                    sortAssignmentsByDate();
                } else {
                    sortAssignmentByCourse();
                }
                assignmentAdapter.notifyDataSetChanged();
            }
        });

        assignmentAdapter = new AssignmentAdapter(assignmentArrayList, AssignmentActivity.this);

        recyclerView.setAdapter(assignmentAdapter);

        homeButton = findViewById(R.id.assignmentsHomeButton);
        addButton = findViewById(R.id.assignmentsAdd);
        dateSort = findViewById(R.id.assignmentsDateSort);
        courseSort = findViewById(R.id.assignmentsCourseSort);
        assignmentsName = findViewById(R.id.assignmentsName);
        assignmentsDueMonth = findViewById(R.id.assignmentsDueMonth);
        assignmentsDueDay = findViewById(R.id.assignmentsDueDay);
        assignmentsDueYear = findViewById(R.id.assignmentsDueYear);
        assignmentsClass = findViewById(R.id.assignmentsClass);

        //Listen to when Add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = assignmentsName.getText().toString();
                int month = Integer.parseInt(assignmentsDueMonth.getText().toString());
                int day = Integer.parseInt(assignmentsDueDay.getText().toString());
                int year = Integer.parseInt(assignmentsDueYear.getText().toString());
                String assignmentClass = assignmentsClass.getText().toString();
                Assignment newAssignment = new Assignment(name, month, day, year, assignmentClass);
                schedulerViewModel.addNewAssignment(newAssignment);
                assignmentsName.setText("");
                assignmentsDueMonth.setText("");
                assignmentsDueDay.setText("");
                assignmentsDueYear.setText("");
                assignmentsClass.setText("");
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AssignmentActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = AssignmentSort.DATE;
                sortAssignmentsByDate();
                assignmentAdapter.notifyDataSetChanged();
            }
        });

        courseSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = AssignmentSort.COURSE;
                sortAssignmentByCourse();
                assignmentAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new AssignmentsTouchHelper(assignmentAdapter, schedulerViewModel));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void sortAssignmentsByDate() {
        assignmentArrayList.sort((Assignment a1, Assignment a2) -> {
            if (a1.getAssignmentDueDateYear() != a2.getAssignmentDueDateYear()) {
                return Integer.compare(a1.getAssignmentDueDateYear(), a2.getAssignmentDueDateYear());
            }

            if (a1.getAssignmentDueDateMonth() != a2.getAssignmentDueDateMonth()) {
                return Integer.compare(a1.getAssignmentDueDateMonth(), a2.getAssignmentDueDateMonth());
            }
            return Integer.compare(a1.getAssignmentDueDateDay(), a2.getAssignmentDueDateDay());
        });
    }

    private void sortAssignmentByCourse() {
        //Sort increasing alphabetically
        assignmentArrayList.sort((Assignment a1, Assignment a2) -> {
            return a1.getAssignmentAssociatedClass().toUpperCase().compareTo(a2.getAssignmentAssociatedClass().toUpperCase());
        });
    }


}