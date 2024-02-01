package com.example.collegescheduler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.collegescheduler.DAOs.AssignmentDAO;
import com.example.collegescheduler.DAOs.CourseDAO;
import com.example.collegescheduler.DAOs.ExamDAO;
import com.example.collegescheduler.DAOs.TodoDAO;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.entities.Course;
import com.example.collegescheduler.entities.Exam;
import com.example.collegescheduler.entities.Todo;

@Database(entities = {Assignment.class, Course.class, Exam.class, Todo.class}, version = 1)
public abstract class SchedulerDatabase extends RoomDatabase {
    public abstract AssignmentDAO getAssignmentDAO();

    public abstract CourseDAO getCourseDAO();

    public abstract ExamDAO getExamDAO();

    public abstract TodoDAO getTodoDAO();

    private static SchedulerDatabase dbInstance;

    public static synchronized SchedulerDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), SchedulerDatabase.class, "scheduler_db").fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }

}
