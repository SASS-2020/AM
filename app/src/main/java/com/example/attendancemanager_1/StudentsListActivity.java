package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class StudentsListActivity extends AppCompatActivity {

    private ClassesInfoHolder selectedClass;
    private ArrayList<StudentInfoHolder> studentInfoHolderList;
    private ArrayList<DatesInfoHolder> datesInfoHolderList;
    private RecyclerView studentListRecyclerView;
    private RecyclerView datesRecyclerView;
    private RecyclerView attendanceRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            selectedClass = (ClassesInfoHolder) intent.getSerializableExtra("selectedClass");
        }
        //Toast.makeText(StudentsListActivity.this, selectedClass.getSubjectName()+""+selectedClass.getSubjectCode(), Toast.LENGTH_LONG).show();//Test
        //setTabnView();
        getStudentList();
        getDatesList();
        setTabnView();

    }

    public void setTabnView() {
        TabLayout tabLayout = findViewById(R.id.tablayout);

        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new CustomPagerAdapter(this, studentInfoHolderList, datesInfoHolderList));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,

                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0: {
                                tab.setText("Students");
                                tab.setIcon(R.drawable.ic_student_list);
                                break;
                            }
                            case 1: {
                                tab.setText("Dates");
                                tab.setIcon(R.drawable.ic_dates);
                                break;
                            }
                            case 2: {
                                tab.setText("Attendance");
                                tab.setIcon(R.drawable.ic_attendance);
                            }
                        }
                    }
                }
        );
        tabLayoutMediator.attach();
    }

    public void getStudentList() {
        studentInfoHolderList = new ArrayList<StudentInfoHolder>();
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012000", 0, 0));
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012001", 0, 0));
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012002", 0, 0));
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012003", 0, 0));
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012004", 0, 0));
        studentInfoHolderList.add(new StudentInfoHolder("SomeName", "1741012005", 0, 0));

    }

    public void getDatesList() {
        datesInfoHolderList = new ArrayList<DatesInfoHolder>();
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-4"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-5"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-6"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-7"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-8"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-9"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-10"));
        datesInfoHolderList.add(new DatesInfoHolder("2020-12-11"));
    }
    public void showLoading()
    {

    }
    public void hideLoading()
    {

    }
}