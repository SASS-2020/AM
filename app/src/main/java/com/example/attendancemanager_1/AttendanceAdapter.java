package com.example.attendancemanager_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceCustomViewHolder> {

    private final ArrayList<StudentInfoHolder> attendanceList;

    public static class AttendanceCustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameView;
        TextView regdView;
        public AttendanceCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.tvname);
            regdView = itemView.findViewById(R.id.tvregd);
        }
    }

    public AttendanceAdapter(ArrayList<StudentInfoHolder> list)
    {
        attendanceList = list;
    }

    @NonNull
    @Override
    public AttendanceCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttendanceCustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.students_recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceCustomViewHolder holder, int position) {
        holder.nameView.setText(attendanceList.get(position).getName());
        holder.regdView.setText("Regd. No. : "+attendanceList.get(position).getRegistration());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }


}
