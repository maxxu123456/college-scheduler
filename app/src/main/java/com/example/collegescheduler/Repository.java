package com.example.collegescheduler;

import android.app.Application;
import android.os.Looper;

import com.example.collegescheduler.DAOs.AssignmentDAO;
import com.example.collegescheduler.DAOs.CourseDAO;
import com.example.collegescheduler.DAOs.ExamDAO;
import com.example.collegescheduler.DAOs.TodoDAO;
import com.example.collegescheduler.Entities.Assignment;
import com.example.collegescheduler.Entities.Course;
import com.example.collegescheduler.Entities.Exam;
import com.example.collegescheduler.Entities.Todo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;

import androidx.lifecycle.LiveData;


public class Repository {
    private final AssignmentDAO assignmentDAO;
    private final CourseDAO courseDAO;
    private final ExamDAO examDAO;
    private final TodoDAO todoDAO;

    ExecutorService executor;

    Handler handler;

    public Repository(Application application) {
        SchedulerDatabase schedulerDatabase = SchedulerDatabase.getInstance(application);
        this.assignmentDAO = schedulerDatabase.getAssignmentDAO();
        this.courseDAO = schedulerDatabase.getCourseDAO();
        this.examDAO = schedulerDatabase.getExamDAO();
        this.todoDAO = schedulerDatabase.getTodoDAO();

        executor = Executors.newSingleThreadExecutor();

        handler = new Handler(Looper.getMainLooper());
    }

    public void addAssignment(Assignment assignment) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                assignmentDAO.insert(assignment);
            }
        });
    }

    public void deleteAssignment(Assignment assignment) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                assignmentDAO.delete(assignment);
            }
        });
    }

    public void addCourse(Course course) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.insert(course);
            }
        });
    }

    public void deleteCourse(Course course) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.delete(course);
            }
        });
    }

    public void addExam(Exam exam) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                examDAO.insert(exam);
            }
        });
    }

    public void deleteExam(Exam exam) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                examDAO.delete(exam);
            }
        });
    }

    public void addTodo(Todo todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoDAO.insert(todo);
            }
        });
    }

    public void deleteTodo(Todo todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                todoDAO.delete(todo);
            }
        });
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return assignmentDAO.getAllAssignments();
    }

    public LiveData<List<Course>> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public LiveData<List<Exam>> getAllExams() {
        return examDAO.getAllExams();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return todoDAO.getAllTodos();
    }
}
