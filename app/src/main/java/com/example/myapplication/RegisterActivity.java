package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText emailET, passwordET;
    private Button signupBtn;
    private TextView loginRedirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.email_edit_text);
        passwordET = findViewById(R.id.password_edit_text);
        signupBtn = findViewById(R.id.signup_button);
        loginRedirection = findViewById(R.id.login_navigation_text_view);

        auth = FirebaseAuth.getInstance();

        loginRedirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    public void createUserFunction(View view) {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if(email.isEmpty()) emailET.setError("Email cannot be empty");
        if(password.isEmpty()) passwordET.setError("Password cannot be empty");

        else{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Failed! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}