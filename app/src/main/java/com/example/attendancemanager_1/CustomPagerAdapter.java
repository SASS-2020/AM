package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class CustomPagerAdapter extends FragmentStateAdapter {
    private ArrayList<StudentInfoHolder> arg0;
    private ArrayList<DatesInfoHolder> arg1;
    public CustomPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<StudentInfoHolder> arg0, ArrayList<DatesInfoHolder> arg1) {
        super(fragmentActivity);
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:
                return StudentListFragment.newInstance(arg0);
            case 1:
                return DatesFragment.newInstance(arg1);
            default:
                return AttendanceFragment.newInstance(arg0);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
