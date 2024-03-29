package com.example.collegescheduler.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.collegescheduler.entities.Assignment;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert
    void insert(Assignment assignment);

    @Delete
    void delete(Assignment assignment);
//    @Update
//    void update();

    @Query("SELECT * FROM  assignments_table")
    LiveData<List<Assignment>> getAllAssignments();
}
