package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Classes_Activity extends AppCompatActivity implements CustomClassesDialog.CustomClassesDialogListener {

    private RecyclerView mRecyclerView = null;
    private RecyclerView.LayoutManager mLayoutManager = null;
    private ClassesCustomAdapter mAdapter = null;
    private ArrayList<ClassesInfoHolder> classesInfoHolderList;
    private FloatingActionButton floatingAddButton;
    private FirebaseFirestore db;
    private CollectionReference cRef;
    private final int countClasses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        floatingAddButton = findViewById(R.id.floating_button_add_classes);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomClassesDialog();
            }
        });
        db = FirebaseFirestore.getInstance();
        cRef = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Classes");//+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Classes");
        classesInfoHolderList = new ArrayList<>();
        createRecyclerView();
        getClassesInfoHolderList();
    }


    public void showCustomClassesDialog() {
        DialogFragment dialog = new CustomClassesDialog();
        dialog.show(getSupportFragmentManager(), "CustomClassesDialog");
    }

    public void createRecyclerView() {
        mRecyclerView = findViewById(R.id.classes_recyclerView);
        //getClassesInfoHolderList();
        mAdapter = new ClassesCustomAdapter(classesInfoHolderList, this);
        mAdapter.setOnClickListener(new ClassesCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(Classes_Activity.this, StudentsListActivity.class);
                intent.putExtra("selectedClass", classesInfoHolderList.get(position));
                startActivity(intent);
            }
        });
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getClassesInfoHolderList() {//Gets data from Database
        //final ArrayList<ClassesInfoHolder> infoHolder = new ArrayList<>();
        cRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        classesInfoHolderList.add(document.toObject(ClassesInfoHolder.class));
                    }
                    if (classesInfoHolderList.size() == 0) {
                        Toast.makeText(Classes_Activity.this, "It's Lonely Here", Toast.LENGTH_LONG).show();
                    }
                    notifyChanges();
                }
            }
        });
    }

    public void addToClassesInfoHolderList(ClassesInfoHolder newClassesInfoHolder) {
        classesInfoHolderList.add(newClassesInfoHolder);
        mAdapter.notifyItemInserted(classesInfoHolderList.size() - 1);
        cRef.add(newClassesInfoHolder).addOnSuccessListener(new CustomSuccessListener(newClassesInfoHolder)).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Classes_Activity.this, "Something's Wrong4", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void notifyChanges() {
        mAdapter.notifyDataSetChanged();
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }

    private static class CustomSuccessListener implements OnSuccessListener<DocumentReference> {
        private ClassesInfoHolder mInfo;

        CustomSuccessListener() {
        }

        CustomSuccessListener(ClassesInfoHolder mInfo) {
            this.mInfo = mInfo;
        }

        @Override
        public void onSuccess(DocumentReference documentReference) {
            mInfo.setDocID(documentReference.getId());
        }
    }
}