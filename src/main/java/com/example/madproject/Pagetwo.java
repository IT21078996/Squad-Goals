package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Pagetwo extends AppCompatActivity {

    private Button btnDone;

    //private EditText txt4;
    //private Object EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pagetwo);

        EditText txt4 = (EditText) findViewById(R.id.txt4);

        btnDone = (Button) findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openPageThree();
            }
        });

    }

    private void openPageThree() {
        Intent intent = new Intent(this,Pagethree.class);
        startActivity(intent);
    }
       // EditText = (EditText) findViewById(R.id.txt4);

        //Intent intent = getIntent();
        //String pagetwodata = intent.getStringExtra("");
        //EditText.setText(pagetwodata);
    }