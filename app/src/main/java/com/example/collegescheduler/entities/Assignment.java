package com.example.collegescheduler.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignments_table")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "assignment_id")
    private int assignmentID;

    @ColumnInfo(name = "assignment_name")
    private String assignmentName;

    @ColumnInfo(name = "assignment_due_date_month")
    private int assignmentDueDateMonth;

    @ColumnInfo(name = "assignment_due_date_day")
    private int assignmentDueDateDay;

    @ColumnInfo(name = "assignment_due_date_year")
    private int assignmentDueDateYear;

    @ColumnInfo(name = "assignment_associated_class")
    private String assignmentAssociatedClass;

    public Assignment(String assignmentName, int assignmentDueDateMonth, int assignmentDueDateDay, int assignmentDueDateYear, String assignmentAssociatedClass) {
        this.assignmentName = assignmentName;
        this.assignmentDueDateMonth = assignmentDueDateMonth;
        this.assignmentDueDateDay = assignmentDueDateDay;
        this.assignmentDueDateYear = assignmentDueDateYear;
        this.assignmentAssociatedClass = assignmentAssociatedClass;
    }

    public Assignment() {

    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getAssignmentDueDateMonth() {
        return assignmentDueDateMonth;
    }

    public void setAssignmentDueDateMonth(int assignmentDueDateMonth) {
        this.assignmentDueDateMonth = assignmentDueDateMonth;
    }

    public int getAssignmentDueDateDay() {
        return assignmentDueDateDay;
    }

    public void setAssignmentDueDateDay(int assignmentDueDateDay) {
        this.assignmentDueDateDay = assignmentDueDateDay;
    }

    public int getAssignmentDueDateYear() {
        return assignmentDueDateYear;
    }

    public void setAssignmentDueDateYear(int assignmentDueDateYear) {
        this.assignmentDueDateYear = assignmentDueDateYear;
    }

    public String getAssignmentAssociatedClass() {
        return assignmentAssociatedClass;
    }

    public void setAssignmentAssociatedClass(String assignmentAssociatedClass) {
        this.assignmentAssociatedClass = assignmentAssociatedClass;
    }
}
