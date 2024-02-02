package com.example.collegescheduler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.Activities.ExamActivity;
import com.example.collegescheduler.R;
import com.example.collegescheduler.databinding.ExamListItemBinding;
import com.example.collegescheduler.entities.Exam;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private ArrayList<Exam> exams;

    private ExamActivity activity;

    public ExamAdapter(ArrayList<Exam> exams, ExamActivity activity) {
        this.exams = exams;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExamListItemBinding examListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.exam_list_item,
                parent,
                false

        );
        return new ExamViewHolder(examListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ExamViewHolder holder, int position) {
        Exam current = exams.get(position);
        holder.examListItemBinding.setExam(current);
    }

    @Override
    public int getItemCount() {
        if (exams != null) {
            return exams.size();
        } else {
            return 0;
        }
    }

    public void setExams(ArrayList<Exam> exam) {
        this.exams = exams;
        notifyDataSetChanged();
    }

    public Exam getItem(int position) {
        return exams.get(position);
    }

    public Context getContext() {
        return activity;
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {
        private ExamListItemBinding examListItemBinding;

        public ExamViewHolder(@NonNull ExamListItemBinding examListItemBinding) {
            super(examListItemBinding.getRoot());
            this.examListItemBinding = examListItemBinding;
        }
    }
}
