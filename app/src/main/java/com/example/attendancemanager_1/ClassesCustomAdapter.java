package com.example.attendancemanager_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesCustomAdapter extends RecyclerView.Adapter<ClassesCustomAdapter.ClassesCustomViewHolder> {

    private ArrayList<ClassesInfoHolder> classesList;
    public static class ClassesCustomViewHolder extends RecyclerView.ViewHolder {

        TextView textSubjectName;
        TextView textSubjectCode;
        TextView textSection;

        public ClassesCustomViewHolder(@NonNull View itemView) {

            super(itemView);
            textSubjectName = itemView.findViewById(R.id.subject_name);
            textSubjectCode = itemView.findViewById(R.id.subject_code);
            textSection = itemView.findViewById(R.id.section);
        }
    }

    public ClassesCustomAdapter(ArrayList<ClassesInfoHolder> list)
    {
        classesList = list;
    }

    @NonNull
    @Override
    public ClassesCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassesCustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesCustomViewHolder holder, int position) {
        ClassesInfoHolder temp = classesList.get(position);
        holder.textSubjectName.setText(temp.getSubjectName());
        holder.textSubjectCode.setText(temp.getSubjectCode());
        holder.textSection.setText(temp.getSection());
    }

    @Override
    public int getItemCount() {
        return classesList.size();
    }
}
