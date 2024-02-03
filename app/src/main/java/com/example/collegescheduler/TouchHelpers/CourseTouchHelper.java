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

import com.example.collegescheduler.Adapters.CourseAdapter;
import com.example.collegescheduler.Adapters.ExamAdapter;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Assignment;
import com.example.collegescheduler.entities.Course;

public class CourseTouchHelper extends ItemTouchHelper.SimpleCallback {

    private CourseAdapter adapter;
    private SchedulerViewModel schedulerViewModel;

    public CourseTouchHelper(CourseAdapter adapter, SchedulerViewModel schedulerViewModel) {
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
            builder.setTitle("Delete Course");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    schedulerViewModel.deleteCourse(adapter.getItem(position));
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
            View dialogView = inflater.inflate(R.layout.course_edit_item, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Edit Course");

            final EditText courseName = dialogView.findViewById(R.id.edit_course_name);
            final EditText courseInstructor = dialogView.findViewById(R.id.edit_course_instructor);
            final EditText courseTime = dialogView.findViewById(R.id.edit_course_time);
            courseName.setInputType(InputType.TYPE_CLASS_TEXT);
            courseInstructor.setInputType(InputType.TYPE_CLASS_TEXT);
            courseTime.setInputType(InputType.TYPE_CLASS_TEXT);

            Course course = adapter.getItem(position);
            courseName.setHint(String.valueOf(course.getCourseName()));
            courseInstructor.setHint(String.valueOf(course.getCourseInstructor()));
            courseTime.setHint(String.valueOf(course.getCourseTime()));

            builder.setView(dialogView);
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Course newCourse = new Course();
                    if(courseName.getText().toString().length() != 0){
                        newCourse.setCourseName(courseName.getText().toString());
                    } else {
                        newCourse.setCourseName(course.getCourseName());
                    }

                    if(courseInstructor.getText().toString().length() != 0){
                        newCourse.setCourseInstructor(courseInstructor.getText().toString());
                    } else {
                        newCourse.setCourseInstructor(course.getCourseInstructor());
                    }

                    if(courseTime.getText().toString().length() != 0){
                        newCourse.setCourseTime(courseTime.getText().toString());
                    } else {
                        newCourse.setCourseTime(course.getCourseTime());
                    }

                    schedulerViewModel.addNewCourse(newCourse);
                    schedulerViewModel.deleteCourse(adapter.getItem(position));
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
