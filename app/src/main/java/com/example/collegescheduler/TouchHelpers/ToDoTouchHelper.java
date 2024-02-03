package com.example.collegescheduler.TouchHelpers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.Adapters.TodoAdapter;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Todo;

import java.util.ArrayList;

public class ToDoTouchHelper extends ItemTouchHelper.SimpleCallback{

    private TodoAdapter adapter;
    private SchedulerViewModel schedulerViewModel;

    public ToDoTouchHelper(TodoAdapter adapter, SchedulerViewModel model) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.schedulerViewModel = model;
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.RIGHT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    schedulerViewModel.deleteTodo(adapter.getItem(position));
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(position);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Edit Task");

            final EditText input = new EditText(adapter.getContext());
            input.setHint(adapter.getItem(position).getTodoTask());
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().toString().length() == 0) {
                        adapter.notifyDataSetChanged();
                    } else {
                        Todo newTask = new Todo();
                        newTask.setTodoTask(input.getText().toString());
                        schedulerViewModel.addNewTodo(newTask);
                        schedulerViewModel.deleteTodo(adapter.getItem(position));
                        adapter.notifyItemChanged(position);
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();
        }
    }
}
