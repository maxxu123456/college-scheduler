package com.example.collegescheduler.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegescheduler.databinding.CourseListItemBinding;
import com.example.collegescheduler.R;
import com.example.collegescheduler.entities.Course;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CourseListItemBinding courseListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.course_list_item,
                parent,
                false

        );
        return new CourseViewHolder(courseListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        Course current = courses.get(position);
        holder.courseListItemBinding.setCourse(current);
    }

    @Override
    public int getItemCount() {
        if (courses != null) {
            return courses.size();
        } else {
            return 0;
        }
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private CourseListItemBinding courseListItemBinding;

        public CourseViewHolder(@NonNull CourseListItemBinding courseListItemBinding) {
            super(courseListItemBinding.getRoot());
            this.courseListItemBinding = courseListItemBinding;
        }
    }
}
