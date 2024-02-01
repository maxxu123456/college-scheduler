package com.example.collegescheduler.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos_table")
public class Todo {
    @ColumnInfo(name = "todo_id")
    @PrimaryKey(autoGenerate = true)
    private int todoID;

    @ColumnInfo(name = "todo_task")
    private String todoTask;

    @ColumnInfo(name = "todo_status")
    private boolean todoStatus;

    public Todo(String task, boolean status) {
        this.todoTask = task;
        this.todoStatus = status;
    }

    public Todo() {
    }

    public int getTodoID() {
        return todoID;
    }

    public void setTodoID(int todoID) {
        this.todoID = todoID;
    }

    public String getTodoTask() {
        return todoTask;
    }

    public void setTodoTask(String todoTask) {
        this.todoTask = todoTask;
    }

    public boolean isTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(boolean todoStatus) {
        this.todoStatus = todoStatus;
    }
}
