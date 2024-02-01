package com.example.collegescheduler.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.collegescheduler.entities.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM courses_table")
    LiveData<List<Course>> getAllCourses();
}
