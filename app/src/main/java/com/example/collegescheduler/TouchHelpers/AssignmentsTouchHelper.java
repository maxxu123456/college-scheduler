package com.example.collegescheduler.TouchHelpers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.Adapters.AssignmentAdapter;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.entities.Exam;

public class AssignmentsTouchHelper extends ItemTouchHelper.SimpleCallback {

    private AssignmentAdapter adapter;
    private SchedulerViewModel schedulerViewModel;

    public AssignmentsTouchHelper(AssignmentAdapter adapter, SchedulerViewModel schedulerViewModel) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.schedulerViewModel = schedulerViewModel;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.RIGHT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Assignment");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    schedulerViewModel.deleteAssignment(adapter.getItem(position));
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(position);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            LayoutInflater inflater = LayoutInflater.from(adapter.getContext());
            View dialogView = inflater.inflate(R.layout.assignment_edit_item, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Edit Assigment");


            final EditText assignmentName = dialogView.findViewById(R.id.edit_assignment_name);
            final EditText assignmentMonth = dialogView.findViewById(R.id.edit_assigment_month);
            final EditText assignmentYear = dialogView.findViewById(R.id.edit_assigment_year);
            final EditText assignmentDay = dialogView.findViewById(R.id.edit_assignment_day);
            final EditText assignmentClass = dialogView.findViewById(R.id.edit_assigment_class);
            assignmentName.setInputType(InputType.TYPE_CLASS_TEXT);
            assignmentMonth.setInputType(InputType.TYPE_CLASS_TEXT);
            assignmentYear.setInputType(InputType.TYPE_CLASS_TEXT);
            assignmentDay.setInputType(InputType.TYPE_CLASS_TEXT);
            assignmentClass.setInputType(InputType.TYPE_CLASS_TEXT);

            Assignment assignment = adapter.getItem(position);
            assignmentName.setHint(String.valueOf(assignment.getAssignmentName()));
            assignmentMonth.setHint(String.valueOf(assignment.getAssignmentDueDateMonth()));
            assignmentDay.setHint(String.valueOf(assignment.getAssignmentDueDateDay()));
            assignmentYear.setHint(String.valueOf(assignment.getAssignmentDueDateYear()));
            assignmentClass.setHint(String.valueOf(assignment.getAssignmentAssociatedClass()));

            builder.setView(dialogView);
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Assignment newAssigment = new Assignment();
                    if(assignmentName.getText().toString().length() != 0){
                        newAssigment.setAssignmentName(assignmentName.getText().toString());
                    } else {
                        newAssigment.setAssignmentName(assignment.getAssignmentName());
                    }

                    if(assignmentClass.getText().toString().length() != 0){
                        newAssigment.setAssignmentAssociatedClass(assignmentClass.getText().toString());
                    } else {
                        newAssigment.setAssignmentAssociatedClass(assignment.getAssignmentAssociatedClass());
                    }

                    if(assignmentMonth.getText().toString().length() != 0){
                        newAssigment.setAssignmentDueDateMonth(Integer.getInteger(assignmentMonth.getText().toString()));
                    } else {
                        newAssigment.setAssignmentDueDateMonth(assignment.getAssignmentDueDateMonth());
                    }

                    if(assignmentDay.getText().toString().length() != 0){
                        newAssigment.setAssignmentDueDateDay(Integer.getInteger(assignmentDay.getText().toString()));
                    } else {
                        newAssigment.setAssignmentDueDateDay(assignment.getAssignmentDueDateDay());
                    }

                    if(assignmentYear.getText().toString().length() != 0){
                        newAssigment.setAssignmentDueDateYear(Integer.getInteger(assignmentYear.getText().toString()));
                    } else {
                        newAssigment.setAssignmentDueDateYear(assignment.getAssignmentDueDateYear());
                    }
                    schedulerViewModel.addNewAssignment(newAssigment);
                    schedulerViewModel.deleteAssignment(adapter.getItem(position));
                    adapter.notifyItemChanged(position);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyDataSetChanged();
                }
            });
            builder.show();
        }
    }
}
