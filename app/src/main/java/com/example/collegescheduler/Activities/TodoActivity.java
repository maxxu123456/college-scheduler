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


import com.example.collegescheduler.Adapters.ExamAdapter;
import com.example.collegescheduler.Adapters.TodoAdapter;
import com.example.collegescheduler.MainActivity;
import com.example.collegescheduler.databinding.ActivityExamBinding;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerDatabase;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.databinding.ActivityTodoBinding;
import com.example.collegescheduler.entities.Exam;
import com.example.collegescheduler.entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {
    Button homeButton;
    Button addButton;

    EditText todosTask;

    private SchedulerDatabase schedulerDatabase;
    private ArrayList<Todo> todoArrayList = new ArrayList<>();

    private TodoAdapter todoAdapter;

    private ActivityTodoBinding todoBinding;

    SchedulerViewModel schedulerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);


        todoBinding = DataBindingUtil.setContentView(this, R.layout.activity_todo);

        RecyclerView recyclerView = todoBinding.todosRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        schedulerDatabase = SchedulerDatabase.getInstance(this);

        schedulerViewModel = new ViewModelProvider(this).get(SchedulerViewModel.class);

        schedulerViewModel.getAllTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                todoArrayList.clear();
                for (Todo a : todos) {
                    todoArrayList.add(a);
                }
                todoAdapter.notifyDataSetChanged();
            }
        });

        todoAdapter = new TodoAdapter(todoArrayList);

        recyclerView.setAdapter(todoAdapter);

        homeButton = findViewById(R.id.todosHomeButton);
        addButton = findViewById(R.id.todosAdd);

        todosTask = findViewById(R.id.todosTask);

        //Listen to when Add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = todosTask.getText().toString();
                schedulerViewModel.addNewTodo(new Todo(task, false));
                todosTask.setText("");

            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TodoActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}