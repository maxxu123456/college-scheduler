package com.example.collegescheduler.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses_table")
public class Course {
    @ColumnInfo(name = "course_id")
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    @ColumnInfo(name = "course_name")
    private String courseName;

    @ColumnInfo(name = "course_time")
    private String courseTime;

    @ColumnInfo(name = "course_instructor")
    private String courseInstructor;

    public Course(int courseId, String courseName, String courseTime, String courseInstructor) {
        this.courseID = courseId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.courseInstructor = courseInstructor;
    }

    public Course() {

    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
}
