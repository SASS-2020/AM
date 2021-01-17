package com.example.attendancemanager_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.DatesCustomVierwHolder> {
    private final ArrayList<DatesInfoHolder> datesList;

    public static class DatesCustomVierwHolder extends RecyclerView.ViewHolder
    {
        TextView tvDates;
        public DatesCustomVierwHolder(@NonNull View itemView) {
            super(itemView);
            tvDates = itemView.findViewById(R.id.datestv);
        }
    }

    public DatesAdapter(ArrayList<DatesInfoHolder> list)
    {
        datesList = list;
    }

    @NonNull
    @Override
    public DatesCustomVierwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DatesCustomVierwHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dates_recycler_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DatesCustomVierwHolder holder, int position) {
        holder.tvDates.setText(datesList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return datesList.size();
    }


}
