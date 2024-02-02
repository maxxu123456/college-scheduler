package com.example.collegescheduler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.Activities.AssignmentActivity;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.R;
import com.example.collegescheduler.databinding.AssignmentListItemBinding;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private ArrayList<Assignment> assignments;

    private AssignmentActivity activity;

    public AssignmentAdapter(ArrayList<Assignment> assignments, AssignmentActivity activity) {
        this.assignments = assignments;
        this.activity = activity;
    }


    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AssignmentListItemBinding assignmentListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.assignment_list_item,
                parent,
                false

        );
        return new AssignmentViewHolder(assignmentListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder holder, int position) {
        Assignment current = assignments.get(position);
        holder.assignmentListItemBinding.setAssignment(current);
    }

    @Override
    public int getItemCount() {
        if (assignments != null) {
            return assignments.size();
        } else {
            return 0;
        }
    }

    public Assignment getItem(int position) {
        return assignments.get(position);
    }

    public Context getContext() {
        return activity;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
        notifyDataSetChanged();
    }

    class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private AssignmentListItemBinding assignmentListItemBinding;

        public AssignmentViewHolder(@NonNull AssignmentListItemBinding assignmentListItemBinding) {
            super(assignmentListItemBinding.getRoot());
            this.assignmentListItemBinding = assignmentListItemBinding;
        }
    }
}
