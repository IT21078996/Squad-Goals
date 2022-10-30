package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button goBack, login;
    TextView signup, up;
    EditText etxtun, etxtpw;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        up = findViewById(R.id.welcome);
        goBack = findViewById(R.id.btnBack);

        etxtun = findViewById(R.id.eTUsername);
        etxtpw = findViewById(R.id.eTPassword);

        signup = findViewById(R.id.signuplink);

        login = findViewById(R.id.btnUpdate);

        up.setOnClickListener(this);
        goBack.setOnClickListener(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.welcome:
                startActivity(new Intent(this, UserProfileActivity.class));
                break;
            case R.id.btnBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnUpdate:
                loginUser();
                break;
            case R.id.signuplink:link:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }

    private void loginUser() {
        String email = etxtun.getText().toString().trim();
        String password = etxtpw.getText().toString().trim();

        if (email.isEmpty()) {
            etxtun.setError("Email is required!");
            etxtun.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etxtun.setError("Please provide valid email address!");
            etxtun.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etxtpw.setError("Password is required!");
            etxtpw.requestFocus();
            return;
        }
        if (password.length()<6) {
            etxtpw.setError("Password length should be 6 characters!");
            etxtpw.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}