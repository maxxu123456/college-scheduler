package com.example.collegescheduler.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exams_table")
public class Exam {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exam_id")
    private int examID;

    @ColumnInfo(name = "exam_month")
    private int examMonth;

    @ColumnInfo(name = "exam_day")
    private int examDay;

    @ColumnInfo(name = "exam_year")
    private int examYear;

    @ColumnInfo(name = "exam_time")
    private String examTime;

    @ColumnInfo(name = "exam_location")
    private String examLocation;

    public Exam(int examID, int examMonth, int examDay, int examYear, String examTime, String examLocation) {
        this.examID = examID;
        this.examMonth = examMonth;
        this.examDay = examDay;
        this.examYear = examYear;
        this.examTime = examTime;
        this.examLocation = examLocation;
    }

    public Exam() {
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public int getExamMonth() {
        return examMonth;
    }

    public void setExamMonth(int examMonth) {
        this.examMonth = examMonth;
    }

    public int getExamDay() {
        return examDay;
    }

    public void setExamDay(int examDay) {
        this.examDay = examDay;
    }

    public int getExamYear() {
        return examYear;
    }

    public void setExamYear(int examYear) {
        this.examYear = examYear;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamLocation() {
        return examLocation;
    }

    public void setExamLocation(String examLocation) {
        this.examLocation = examLocation;
    }
}
