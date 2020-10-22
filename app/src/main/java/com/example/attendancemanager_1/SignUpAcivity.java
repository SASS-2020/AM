package com.example.attendancemanager_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class SignUpAcivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout = null;
    private EditText mailEditTextSignUp = null;
    private EditText passwordEditTextSignUp = null;
    private Button buttonSignUp = null;
    private FirebaseAuth mAuth = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acivity);
        constraintLayout = findViewById(R.id.id_signuppage);
        mailEditTextSignUp = findViewById(R.id.editTextEmailAddressSignUp);
        passwordEditTextSignUp = findViewById(R.id.editTextPasswordSignUp);
        buttonSignUp = findViewById(R.id.button_signupPage);
        mAuth = FirebaseAuth.getInstance();
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                mailEditTextSignUp.clearFocus();
                passwordEditTextSignUp.clearFocus();
                return true;
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mailEditTextSignUp.getText().toString().length() == 0)
                {
                    mailEditTextSignUp.requestFocus();
                }
                if(passwordEditTextSignUp.getText().toString().length() <= 6)
                {
                    Toast.makeText(SignUpAcivity.this,"Password too short !",Toast.LENGTH_LONG).show();
                    passwordEditTextSignUp.requestFocus();
                }
                signUp(mailEditTextSignUp.getText().toString(), passwordEditTextSignUp.getText().toString());
            }
        });

    }
    private void signUp(String mailID, String password)
    {
        mAuth.createUserWithEmailAndPassword(mailID, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //Log.d("success", "createUserWithEmail:success");
                    Toast.makeText(SignUpAcivity.this, "Hola!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(SignUpAcivity.this, "Oh No!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}