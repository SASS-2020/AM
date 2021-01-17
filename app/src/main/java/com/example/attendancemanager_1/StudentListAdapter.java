package com.example.attendancemanager_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentCustomViewHolder>{
    private final ArrayList<StudentInfoHolder> studentList;


    public static class StudentCustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameView;
        TextView regdView;
        public StudentCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.tvname);
            regdView = itemView.findViewById(R.id.tvregd);
        }
    }

    public StudentListAdapter(ArrayList<StudentInfoHolder> list)
    {
        studentList = list;
    }

    @NonNull
    @Override
    public StudentCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentCustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.students_recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentCustomViewHolder holder, int position) {
        holder.nameView.setText(studentList.get(position).getName());
        holder.regdView.setText("Regd. No. : "+studentList.get(position).getRegistration());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }




}
