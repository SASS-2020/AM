package com.example.attendancemanager_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Classes_Activity extends AppCompatActivity implements CustomClassesDialog.CustomClassesDialogListener {

    private RecyclerView mRecyclerView = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private RecyclerView.Adapter mAdapter = null;
    private ArrayList<ClassesInfoHolder> classesInfoHolderList;
    private FloatingActionButton floatingAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        createRecyclerView();
        floatingAddButton = findViewById(R.id.floating_button_add_classes);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomClassesDialog();
            }
        });

    }

    public void showCustomClassesDialog() {
        DialogFragment dialog = new CustomClassesDialog();
        dialog.show(getSupportFragmentManager(), "CustomClassesDialog");
    }

    public void createRecyclerView() {
        mRecyclerView = findViewById(R.id.classes_recyclerView);
        classesInfoHolderList = getClassesInfoHolderList();
        mAdapter = new ClassesCustomAdapter(classesInfoHolderList, this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<ClassesInfoHolder> getClassesInfoHolderList() {
        ArrayList<ClassesInfoHolder> infoHolder = new ArrayList<>();
        infoHolder.add(new ClassesInfoHolder("CSE1011", "SEPA", "A"));
        infoHolder.add(new ClassesInfoHolder("CSE1011", "SEPA", "B"));
        infoHolder.add(new ClassesInfoHolder("CSE1023", "TWP", "A"));
        return infoHolder;
    }

    public void addToClassesInfoHolderList(ClassesInfoHolder newClassesInfoHolder) {
        classesInfoHolderList.add(newClassesInfoHolder);
        mAdapter.notifyItemInserted(classesInfoHolderList.size()-1);
    }
}