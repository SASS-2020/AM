package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CustomPagerAdapter extends FragmentStateAdapter {
    public CustomPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0:
                return new StudentListFragment();
            case 1:
                return new DatesFragment();
            default:
                return new AttendanceFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
