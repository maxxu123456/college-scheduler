package com.example.collegescheduler;

import androidx.room.Database;

import com.example.collegescheduler.Entities.Assignment;
import com.example.collegescheduler.Entities.Course;
import com.example.collegescheduler.Entities.Exam;
import com.example.collegescheduler.Entities.Todo;

@Database(entities = {Assignment.class, Course.class, Exam.class, Todo.class}, version = 1)
public class SchedulerDatabase {

}
