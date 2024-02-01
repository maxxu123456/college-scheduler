package com.example.collegescheduler.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.collegescheduler.Entities.Assignment;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert
    void insert(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM  assignments_table")
    LiveData<List<Assignment>> getAllAssignments();
}
