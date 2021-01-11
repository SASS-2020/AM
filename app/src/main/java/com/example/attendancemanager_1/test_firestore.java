package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class test_firestore extends AppCompatActivity {

    private EditText mEditTextName = null;
    private EditText getmEditTextDate = null;
    private Button mButtonAdd = null;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_firestore);
        db = FirebaseFirestore.getInstance();
        mEditTextName = findViewById(R.id.et_Name);
        getmEditTextDate = findViewById(R.id.et_date);
        mButtonAdd = findViewById(R.id.button_add);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDoc();
            }
        });
    }

    private void saveDoc() {
        String name = mEditTextName.getText().toString();
        String date = getmEditTextDate.getText().toString();
        Map<String, Object> doc = new HashMap<String, Object>();
        doc.put("Date", date);
        db.collection("TestCollection").document(name).set(doc).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(test_firestore.this, "Added", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(test_firestore.this, "Error", Toast.LENGTH_LONG).show();
                Log.d("Err", e.getMessage());
            }
        });
    }
}