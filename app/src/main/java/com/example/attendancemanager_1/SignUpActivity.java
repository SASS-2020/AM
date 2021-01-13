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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout = null;
    private EditText nameEditTextSignup = null;
    private EditText mailEditTextSignUp = null;
    private EditText passwordEditTextSignUp = null;
    private EditText confirmPasswordEditTextSignUp = null;
    private Button buttonSignUp = null;
    private ProgressBar progressBar = null;
    private FirebaseAuth mAuth = null;
    private String name;
    private String emailID;
    private String password;
    private String confirmPassword;
    public static final String USER_NAME = "userName";
    public static final String USER_ID = "userID";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acivity);
        init();
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                mailEditTextSignUp.clearFocus();
                passwordEditTextSignUp.clearFocus();
                return true;
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                signUp();
            }
        });

    }

    private void init() {
        constraintLayout = findViewById(R.id.id_signuppage);
        nameEditTextSignup = findViewById(R.id.editTextName);
        mailEditTextSignUp = findViewById(R.id.editTextEmailAddressSignUp);
        passwordEditTextSignUp = findViewById(R.id.editTextPasswordSignUp);
        confirmPasswordEditTextSignUp = findViewById(R.id.editTextConfirmPasswordSignUp);
        buttonSignUp = findViewById(R.id.button_signupPage);
        progressBar = findViewById(R.id.progressBarSignUp);
        mAuth = FirebaseAuth.getInstance();


    }

    private void signUp() {
        name = nameEditTextSignup.getText().toString();
        emailID = mailEditTextSignUp.getText().toString();
        password = passwordEditTextSignUp.getText().toString();
        confirmPassword = confirmPasswordEditTextSignUp.getText().toString();
        if (name.length() == 0 || emailID.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            hideLoading();
            Toast.makeText(SignUpActivity.this, "Empty Field(s)", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            hideLoading();
            Toast.makeText(SignUpActivity.this, "Password Should be of at least 6 characters", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            hideLoading();
            Toast.makeText(SignUpActivity.this, "Check Password", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailID, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Log.d("success", "createUserWithEmail:success");
                    Toast.makeText(SignUpActivity.this, "Hola!", Toast.LENGTH_LONG).show();
                    signIn();
                } else {
                    Toast.makeText(SignUpActivity.this, "Oh Snap!", Toast.LENGTH_LONG).show();
                    hideLoading();
                }
            }
        });
    }

    private void signIn() {
        Map<String, Object> userData = new HashMap<>();
        userData.put(USER_NAME, name);
        userData.put(USER_ID, emailID);
        try {
            db.collection("Users").document(mAuth.getCurrentUser().getUid()).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        startActivity(new Intent(SignUpActivity.this, Classes_Activity.class));//Test
                        finish();//Test
                    }
                        //addMetaData();
                }
            });

        } catch (NullPointerException e) {
            //Toast.makeText(SignUpActivity.this, "Account Created BUT NOT LOGGED IN", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }

    }

    private void addMetaData() {
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("docCount", "0");
        db.collection("Users").document(mAuth.getCurrentUser().getUid()).collection("Classes").document("Meta").set(metaData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(SignUpActivity.this, Classes_Activity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "Oooops!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}