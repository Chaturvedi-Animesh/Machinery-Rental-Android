package com.chaturvediji.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewCustomer extends AppCompatActivity {
    EditText nm,ag,gen,con,add;
    Button btn;
    FirebaseAuth mAuth;
    DatabaseReference databaseCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        mAuth=FirebaseAuth.getInstance();
        nm=(EditText)findViewById(R.id.Cname);
        ag=(EditText)findViewById(R.id.Cage);
        gen=(EditText)findViewById(R.id.Cgender);
        con=(EditText)findViewById(R.id.Ccontact);
        add=(EditText)findViewById(R.id.Caddress);
        databaseCustomer= FirebaseDatabase.getInstance().getReference("Customer");
        btn=(Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addcustomer();

            }
        });
    }
    private void Addcustomer() {
        String name=nm.getText().toString();
        String age=ag.getText().toString();
        String gender=gen.getText().toString();
        String contact=con.getText().toString();
        String address=add.getText().toString();
        String id=mAuth.getUid();
        CustomerAddition customerAddition=new CustomerAddition(id,name,age,gender,contact,address);
        databaseCustomer.child(id).setValue(customerAddition);
        Toast.makeText(this,"Customer Details Added",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Choose.class);
        startActivity(intent);
    }}
