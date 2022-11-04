package com.example.getwheels1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addcar extends AppCompatActivity {

    Button submit,btnShow, btnUpdate, btnDelete,btnshlowlist;
    EditText txtcarname,txtcarmodel,manuyear,regno,add_details;
    Car car;
    DatabaseReference dbRef;

    private void clearControls() {
        txtcarname.setText("");
        txtcarmodel.setText("");
        manuyear.setText("");
        regno.setText("");
        add_details.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);

        txtcarname =findViewById(R.id.payment_price);
        txtcarmodel = findViewById(R.id.payment_price2);
        manuyear = findViewById(R.id.payment_price3);
        regno = findViewById(R.id.payment_price4);
        add_details = findViewById(R.id.payment_price5);

        btnShow = findViewById(R.id.btnShow);
        btnUpdate =findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete12);
        submit = findViewById(R.id.btnsubmit);
        car = new Car();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Car");

                try {
                    if(TextUtils.isEmpty((txtcarname.getText().toString())))
                        Toast.makeText(getApplicationContext(), "please enter car name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty((txtcarmodel.getText().toString())))
                        Toast.makeText(getApplicationContext(), "please enter car model",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty((manuyear.getText().toString())))
                        Toast.makeText(getApplicationContext(), "please enter manufacture year",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty((regno.getText().toString())))
                        Toast.makeText(getApplicationContext(), "please enter register no",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty((add_details.getText().toString())))
                        Toast.makeText(getApplicationContext(), "please enter details",Toast.LENGTH_SHORT).show();
                    else{
                        car.setCar_Name(txtcarname.getText().toString().trim());
                        car.setCar_model(txtcarmodel.getText().toString().trim());
                        car.setManu_year(manuyear.getText().toString().trim());
                        car.setReg_no(regno.getText().toString().trim());
                        car.setAdd_details(add_details.getText().toString().trim());


                        dbRef.child("car").setValue(car);
                        Toast.makeText(getApplicationContext(), "Data saved sucessfully",Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                }catch (NumberFormatException e){

                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Car").child("car");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            add_details.setText(dataSnapshot.child("add_details").getValue().toString());
                            txtcarname.setText(dataSnapshot.child("car_Name").getValue().toString());
                            txtcarmodel.setText(dataSnapshot.child("car_model").getValue().toString());
                            manuyear.setText(dataSnapshot.child("manu_year").getValue().toString());
                            regno.setText(dataSnapshot.child("reg_no").getValue().toString());

                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Car");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("car")) {
                            try {
                                car.setCar_Name(txtcarname.getText().toString().trim());
                                car.setCar_model(txtcarmodel.getText().toString().trim());
                                car.setManu_year(manuyear.getText().toString().trim());
                                car.setReg_no(regno.getText().toString().trim());
                                car.setAdd_details(txtcarmodel.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Car").child("car");
                                dbRef.setValue(car);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Car");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("car")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Car").child("car");
                            dbRef.removeValue();
                            clearControls();

                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

       /* btnshlowlist.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), userlist.class));
            }
        }));*/

    }
}