package com.example.attendancemanager_1;

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
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                mailEditText.clearFocus();
                passwordEditText.clearFocus();
                return true;
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpAcivity.class));
            }
        });
    }

}