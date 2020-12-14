package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout = null;
    private EditText mailEditText = null;
    private EditText passwordEditText = null;
    private Button buttonSignIn = null;
    private Button buttonSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.id_mainpage);
        mailEditText = findViewById(R.id.editTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.button_signin);
        buttonSignUp = findViewById(R.id.button_register);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                mailEditText.clearFocus();
                passwordEditText.clearFocus();
                return true;
            }
        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpAcivity.class));
            }
        });
    }

    private void signIn() {
        startActivity(new Intent(MainActivity.this, Classes_Activity.class));//FOR TESTING PURPOSE NOT PART OF ACTUAL APP.........ACCESS WITHOUT AUTHENTICATION
        //Uncomment the below after testing.............DO NOT CHANGE THE BELOW CODE
        /*String email_id = mailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (email_id.length() == 0 || password.length() == 0) {
            Toast.makeText(MainActivity.this, "Check Email ID and Password", Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email_id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, Classes_Activity.class));
                } else {

                }
            }
        });*/
    }

}