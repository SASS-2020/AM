package com.example.attendancemanager_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Classes_Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private RecyclerView.Adapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        mRecyclerView = findViewById(R.id.classes_recyclerView);
        mAdapter = new ClassesCustomAdapter(getClassesInfoHolderList());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    ArrayList<ClassesInfoHolder> getClassesInfoHolderList() {
        ArrayList<ClassesInfoHolder> infoHolder = new ArrayList<>();
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : B |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1023", "TWP", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1225", "Information Retrieval", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE3333", "Data Mining", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : B |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1023", "TWP", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1225", "Information Retrieval", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE3333", "Data Mining", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : B |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1023", "TWP", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1225", "Information Retrieval", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE3333", "Data Mining", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1011", "SEPA", "sec : B |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1023", "TWP", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE1225", "Information Retrieval", "sec : A |"));
        infoHolder.add(new ClassesInfoHolder("subject code : CSE3333", "Data Mining", "sec : A |"));
        return infoHolder;
    }
}