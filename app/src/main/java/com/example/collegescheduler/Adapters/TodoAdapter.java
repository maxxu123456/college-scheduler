package com.example.collegescheduler.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.Activities.TodoActivity;
import com.example.collegescheduler.R;
import com.example.collegescheduler.databinding.TodoListItemBinding;
import com.example.collegescheduler.entities.Todo;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<Todo> todos;
    private TodoActivity activity;

    public TodoAdapter(ArrayList<Todo> todos, TodoActivity activity) {
        this.todos = todos;
        this.activity = activity;
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoListItemBinding todoListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.todo_list_item,
                parent,
                false

        );
        return new TodoViewHolder(todoListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        Todo current = todos.get(position);
        holder.todoListItemBinding.setTodo(current);
    }

    public boolean toBoolean(int num) {
        return num != 0;
    }

    @Override
    public int getItemCount() {
        if (todos != null) {
            return todos.size();
        } else {
            return 0;
        }
    }

    public ArrayList<Todo>  getAllTodos() {
        return todos;
    }

    public Todo getItem(int position) {
        return todos.get(position);
    }


    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TodoListItemBinding todoListItemBinding;
//        CheckBox todoCheckBox;

        public TodoViewHolder(@NonNull TodoListItemBinding todoListItemBinding) {
            super(todoListItemBinding.getRoot());
            this.todoListItemBinding = todoListItemBinding;
        }
    }

    public Context getContext() {
        return activity;
    }
}
