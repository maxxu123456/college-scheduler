package com.example.collegescheduler;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.entities.Course;
import com.example.collegescheduler.entities.Exam;
import com.example.collegescheduler.entities.Todo;

import java.util.List;

public class SchedulerViewModel extends AndroidViewModel {

    private Repository myRepository;

    private LiveData<List<Assignment>> allAssignments;
    private LiveData<List<Course>> allCourses;
    private LiveData<List<Exam>> allExams;
    private LiveData<List<Todo>> allTodos;

    public SchedulerViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        allAssignments = myRepository.getAllAssignments();
        return allAssignments;
    }

    public LiveData<List<Course>> getAllCourses() {
        allCourses = myRepository.getAllCourses();
        return allCourses;
    }

    public LiveData<List<Exam>> getAllExams() {
        allExams = myRepository.getAllExams();
        return allExams;
    }

    public LiveData<List<Todo>> getAllTodos() {
        allTodos = myRepository.getAllTodos();
        return allTodos;
    }

    public void addNewAssignment(Assignment assignment) {
        myRepository.addAssignment(assignment);
    }

    public void deleteAssignment(Assignment assignment) {
        myRepository.deleteAssignment(assignment);
    }

    public void addNewCourse(Course course) {
        myRepository.addCourse(course);
    }

    public void deleteCourse(Course course) {
        myRepository.deleteCourse(course);
    }

    public void addNewExam(Exam exam) {
        myRepository.addExam(exam);
    }

    public void deleteExam(Exam exam) {
        myRepository.deleteExam(exam);
    }

    public void addNewTodo(Todo todo) {
        myRepository.addTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        myRepository.deleteTodo(todo);
    }

}
