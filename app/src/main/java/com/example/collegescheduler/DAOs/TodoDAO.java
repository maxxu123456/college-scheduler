package com.example.collegescheduler.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.collegescheduler.Entities.Todo;

import java.util.List;

@Dao
public interface TodoDAO {
    @Insert
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todos_table")
    LiveData<List<Todo>> getAllTodos();
}
