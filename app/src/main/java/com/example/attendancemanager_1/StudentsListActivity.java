package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentsListActivity extends AppCompatActivity implements CustomStudentDialog.CustomStudentDialogListener, StudentListFragment.StudentFragmentListener, DatesFragment.DatesFragmentListener {

    private ClassesInfoHolder selectedClass;
    private ArrayList<StudentInfoHolder> studentInfoHolderList;
    private ArrayList<DatesInfoHolder> datesInfoHolderList;
    private ArrayList<AttendanceInfoHolder> attendanceInfoHolderList;
    private RecyclerView studentListRecyclerView;
    private RecyclerView datesRecyclerView;
    private FloatingActionButton addStudentButton;
    private FloatingActionButton takeAttendanceButton;
    private CollectionReference cRefStudent;
    private CollectionReference cRefDates;

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        cRefStudent = db.collection("Users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Classes/"+selectedClass.getDocID()+"/Students");
        cRefDates = db.collection("Users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Classes/"+selectedClass.getDocID()+"/Date");
        studentInfoHolderList = new ArrayList<StudentInfoHolder>();
        datesInfoHolderList = new ArrayList<DatesInfoHolder>();
        setTabnView();
        getStudentList();
        getDatesList();
        addStudentButton = findViewById(R.id.fabaddstudent);
        takeAttendanceButton = findViewById(R.id.fabtakeattendance);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomStudentDialog();
            }
        });
        takeAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentsListActivity.this, AttendanceTakingActivity.class);
                Bundle extras = new Bundle();
                extras.putParcelableArrayList("StudentList", studentInfoHolderList);
                i.putExtras(extras);
                startActivity(i);
            }
        });
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
                        }
                    }
                }
        );
        tabLayoutMediator.attach();
    }

    public void getStudentList() {
        cRefStudent.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        studentInfoHolderList.add(document.toObject(StudentInfoHolder.class));
                    }
                }
            }
        });


    }

    public void getDatesList() {


    }

    public void showLoading() {

    }

    public void hideLoading() {

    }

    public void showCustomStudentDialog() {
        DialogFragment dialog = new CustomStudentDialog();
        dialog.show(getSupportFragmentManager(), "CustomStudentDialog");
    }

    public void sortList()
    {

    }

    @Override
    public void addToStudentInfoHolderList(StudentInfoHolder studentInfoHolder) {
        studentInfoHolderList.add(studentInfoHolder);
        sortList();
        studentListRecyclerView.getAdapter().notifyDataSetChanged();
        cRefStudent.add(studentInfoHolder).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                //Add complete confirmation
            }
        });
    }

    @Override
    public void attachStudentRecyclerView(RecyclerView recyclerView) {
        studentListRecyclerView = recyclerView;
    }

    @Override
    public void attachDatesRecyclerView(RecyclerView recyclerView) {
        datesRecyclerView = recyclerView;
    }
}