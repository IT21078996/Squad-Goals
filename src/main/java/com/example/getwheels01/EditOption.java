package com.example.getwheels01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditOption extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextNumber;
    Button buttonAddUser;
    ListView listViewUsers;
    List<Customer> customers;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_option);

        findViews();

        initListner();

    }


    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextCar);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        buttonAddUser = (Button) findViewById(R.id.buttonAddUser);
        customers = new ArrayList<>();
    }

    private void initListner() {



        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Customer Customer = customers.get(i);
                CallUpdateAndDeleteDialog(Customer.getUserid(), Customer.getName(), Customer.getCar(), Customer.getConNo(), Customer.getLocation(), Customer.getPDate(), Customer.getDDate());


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
                CustomerList UserAdapter = new CustomerList(EditOption.this, customers);
                listViewUsers.setAdapter(UserAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String userid, String username, final String email, String monumber, String location, String pDate, String dDate) {

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
        updateTextddate.setText(dDate);
        updateTextlocation.setText(location);
        updateTextpdate.setText(pDate);
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

}