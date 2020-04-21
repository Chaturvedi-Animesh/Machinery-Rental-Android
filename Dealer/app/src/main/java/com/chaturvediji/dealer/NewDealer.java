package com.chaturvediji.dealer;

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
public class NewDealer extends AppCompatActivity {
    EditText dn,dl,dba,dif;
    Button Dadd;
    FirebaseAuth mAuth;
    DatabaseReference databaseDealer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dealer);
        mAuth=FirebaseAuth.getInstance();
        dn=(EditText)findViewById(R.id.Dname);
        dl=(EditText)findViewById(R.id.Dloc);
        dba=(EditText)findViewById(R.id.Dbank);
        dif=(EditText)findViewById(R.id.Difsc);
        Dadd=(Button)findViewById(R.id.button5);
        databaseDealer= FirebaseDatabase.getInstance().getReference("Dealer");
        Dadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDealer();
            }});}
    public void AddDealer(){
        String Dname=dn.getText().toString().trim();
        String Dloc=dl.getText().toString().trim();
        String Dbank=dba.getText().toString().trim();
        String Difsc=dif.getText().toString().trim();
        String id=mAuth.getUid();
        DealerAddition dealerAddition=new DealerAddition(id,Dloc,Dname,Dbank,Difsc);
        databaseDealer.child(id).setValue(dealerAddition);
        Toast.makeText(this,"Dealer Details Added",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,Choose.class);
        startActivity(intent);
    }}
