package com.example.getwheels01;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextCar, editTextNumber, editTextlocation, editTextDDate, editTextPDate;
    Button buttonAddUser;
    ListView listViewUsers;
    List<Customer> customers;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        initListner();
    }


    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCar = (EditText) findViewById(R.id.editTextCar);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextlocation = (EditText) findViewById(R.id.editTextlocation);
        editTextDDate = (EditText) findViewById(R.id.editTextDDate);
        editTextPDate = (EditText) findViewById(R.id.editTextPDate);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        buttonAddUser = (Button) findViewById(R.id.buttonAddUser);
        customers = new ArrayList<>();
    }

    private void initListner() {
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customers.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Customer Customer = postSnapshot.getValue(Customer.class);
                    customers.add(Customer);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String userid, String username, final String email, String monumber) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextname);
        final EditText updateTextcar = (EditText) dialogView.findViewById(R.id.updateTextcar);
        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateTextmobileno);
        final EditText updateTextddate = (EditText) dialogView.findViewById(R.id.updateTextddate);
        final EditText updateTextlocation = (EditText) dialogView.findViewById(R.id.updateTextlocation);
        final EditText updateTextpdate = (EditText) dialogView.findViewById(R.id.updateTextpdate);
        updateTextname.setText(username);
        updateTextcar.setText(email);
        updateTextmobileno.setText(monumber);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateUser);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteUser);
        dialogBuilder.setTitle(username);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateTextname.getText().toString().trim();
                String car = updateTextcar.getText().toString().trim();
                String conNo = updateTextmobileno.getText().toString().trim();
                String ddate = updateTextddate.getText().toString().trim();
                String location = updateTextlocation.getText().toString().trim();
                String pdate = updateTextpdate.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(conNo)) {
                            updateUser(userid, name, car, conNo, location, pdate, ddate);
                            b.dismiss();
                        }
                    }
                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(userid);
                b.dismiss();
            }
        });
    }

    private boolean updateUser(String id, String name, String car, String conNo, String location, String PDate, String DDate) {
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Customer").child(id);
        Customer Customer = new Customer(id, name, car, conNo, location, PDate, DDate);
        UpdateReference.setValue(Customer);
        Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Customer").child(id);
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "User Deleted", Toast.LENGTH_LONG).show();
        return true;
    }


    private void addUser() {

        String name = editTextName.getText().toString().trim();
        String car = editTextCar.getText().toString().trim();
        String conNo = editTextNumber.getText().toString().trim();
        String PDate = editTextPDate.getText().toString().trim();
        String DDate = editTextDDate.getText().toString().trim();
        String location = editTextlocation.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(car)){
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(car)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Car", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conNo)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Contact Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PDate)){
            Toast.makeText(getApplicationContext(), "Please Enter Pick Up date", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(DDate)){
            Toast.makeText(getApplicationContext(), "Please Enter Drop Off date", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(location)){
            Toast.makeText(getApplicationContext(), "Please Enter Location", Toast.LENGTH_SHORT).show();
        } else {
            String id = databaseReference.push().getKey();
            Customer Customer = new Customer(id, name, car, conNo, location, PDate, DDate);
            databaseReference.child(id).setValue(Customer);

            editTextName.setText("");
            editTextNumber.setText("");
            editTextCar.setText("");
            editTextPDate.setText("");
            editTextDDate.setText("");
            editTextlocation.setText("");
            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
        }

    }

    public void editOption(View view) {
        startActivity(new Intent(getApplicationContext(),EditOption.class));
    }
}