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

import com.example.collegescheduler.Adapters.ExamAdapter;
import com.example.collegescheduler.R;
import com.example.collegescheduler.SchedulerViewModel;
import com.example.collegescheduler.entities.Exam;

public class ExamTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ExamAdapter adapter;
    private SchedulerViewModel schedulerViewModel;

    public ExamTouchHelper(ExamAdapter adapter, SchedulerViewModel schedulerViewModel) {
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
            builder.setTitle("Delete Exam");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    schedulerViewModel.deleteExam(adapter.getItem(position));
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
            View dialogView = inflater.inflate(R.layout.exam_edit_item, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());


            final EditText examName = dialogView.findViewById(R.id.edit_exam_name);
            final EditText examMonth = dialogView.findViewById(R.id.edit_exam_month);
            final EditText examDay = dialogView.findViewById(R.id.edit_exam_day);
            final EditText examYear = dialogView.findViewById(R.id.edit_exam_year);
            final EditText examTime = dialogView.findViewById(R.id.edit_exam_time);
            final EditText examLocation = dialogView.findViewById(R.id.edit_exam_location);
            examMonth.setInputType(InputType.TYPE_CLASS_TEXT);
            examName.setInputType(InputType.TYPE_CLASS_TEXT);
            examDay.setInputType(InputType.TYPE_CLASS_TEXT);
            examYear.setInputType(InputType.TYPE_CLASS_TEXT);
            examTime.setInputType(InputType.TYPE_CLASS_TEXT);
            examLocation.setInputType(InputType.TYPE_CLASS_TEXT);

            Exam exam = adapter.getItem(position);
            examMonth.setHint(String.valueOf(exam.getExamMonth()));
            examName.setHint(String.valueOf(exam.getExamName()));
            examDay.setHint(String.valueOf(exam.getExamDay()));
            examYear.setHint(String.valueOf(exam.getExamYear()));
            examTime.setHint(String.valueOf(exam.getExamTime()));
            examLocation.setHint(String.valueOf(exam.getExamLocation()));

            builder.setView(dialogView);
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Exam newExam = new Exam();
                    if(examName.getText().toString().length() != 0){
                        newExam.setExamName(examName.getText().toString());
                    } else {
                        newExam.setExamName(exam.getExamName());
                    }

                    if(examLocation.getText().toString().length() != 0){
                        newExam.setExamLocation(examLocation.getText().toString());
                    } else {
                        newExam.setExamLocation(exam.getExamLocation());
                    }

                    if(examTime.getText().toString().length() != 0){
                        newExam.setExamTime(examTime.getText().toString());
                    } else {
                        newExam.setExamTime(exam.getExamTime());
                    }

                    if(examMonth.getText().toString().length() != 0){
                        newExam.setExamMonth(Integer.getInteger(examMonth.getText().toString()));
                    } else {
                        newExam.setExamMonth(exam.getExamMonth());
                    }

                    if(examDay.getText().toString().length() != 0){
                        newExam.setExamDay(Integer.getInteger(examDay.getText().toString()));
                    } else {
                        newExam.setExamDay(exam.getExamDay());
                    }

                    if(examYear.getText().toString().length() != 0){
                        newExam.setExamYear(Integer.getInteger(examYear.getText().toString()));
                    } else {
                        newExam.setExamYear(exam.getExamYear());
                    }
                    schedulerViewModel.addNewExam(newExam);
                    schedulerViewModel.deleteExam(adapter.getItem(position));
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
