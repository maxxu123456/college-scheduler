package com.example.collegescheduler.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.collegescheduler.entities.Exam;

import java.util.List;

@Dao
public interface ExamDAO {
    @Insert
    void insert(Exam exam);

    @Delete
    void delete(Exam exam);

    @Query("SELECT * FROM exams_table")
    LiveData<List<Exam>> getAllExams();
}
