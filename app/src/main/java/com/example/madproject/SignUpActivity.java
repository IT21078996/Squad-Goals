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
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button goBack, signup;
    EditText etxtun, etxtpw, etmobile, etdob, etaddress;
    TextView loginl;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        goBack = findViewById(R.id.btnBack);

        etxtun = findViewById(R.id.eTUsername);
        etxtpw = findViewById(R.id.eTPassword);
        etmobile = findViewById(R.id.eTPhone);
        etdob = findViewById(R.id.editTextDate);
        etaddress = findViewById(R.id.editTextAddress);
        
        signup = findViewById(R.id.btnSignup);
        loginl = findViewById(R.id.loginlink);

        mAuth = FirebaseAuth.getInstance();

        goBack.setOnClickListener(this);
        signup.setOnClickListener(this);
        loginl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnSignup:
                registerUser();
                break;
            case R.id.loginlink:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void registerUser() {
        String email = etxtun.getText().toString().trim();
        String mobile = etmobile.getText().toString().trim();
        String dob = etdob.getText().toString().trim();
        String address = etaddress.getText().toString().trim();
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
        if (mobile.isEmpty()) {
            etmobile.setError("Mobile number is required!");
            etmobile.requestFocus();
            return;
        }
        if (dob.isEmpty()) {
            etdob.setError("Age is required!");
            etdob.requestFocus();
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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(email, mobile, dob, address);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                                Toast.makeText(SignUpActivity.this, "Account created Successfully!", Toast.LENGTH_LONG).show();
                                            }else {
                                                Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}