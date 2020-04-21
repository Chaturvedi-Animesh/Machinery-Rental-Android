package com.chaturvediji.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class NewRent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    Spinner ms;
    DatabaseReference databaseMac;
    List<Machine> machineAdditionList;
    Button btn;
    String selected;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rent);
        listView=(ListView)findViewById(R.id.lv);
        databaseMac= FirebaseDatabase.getInstance().getReference("Machine");
        machineAdditionList=new ArrayList<>();
        listView.setVisibility(View.INVISIBLE);
        btn=(Button)findViewById(R.id.button9);
        ms=(Spinner)findViewById(R.id.spinner);
        mAuth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewthelist();
            }
        });
        ms.setOnItemSelectedListener(this);
    }
    private void viewthelist() {
        Query query=FirebaseDatabase.getInstance().getReference("Machine")
                .orderByChild("type").equalTo(selected);
                query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                machineAdditionList.clear();
                for(DataSnapshot macsnapshot : dataSnapshot.getChildren()){
                    Machine machineAddition=macsnapshot.getValue(Machine.class);
                    machineAdditionList.add(machineAddition);
                }
                MachList adapter=new MachList(NewRent.this,machineAdditionList);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Machine machine=machineAdditionList.get(position);
                changecid(machine.getId(),machine.getCid());
            }
        });}
    private void changecid(String id,String cid) {
        if(cid.equals("null")) {
            databaseMac = FirebaseDatabase.getInstance().getReference("Machine").child(id).child("cid");
            databaseMac.setValue(mAuth.getUid());
            Toast.makeText(this, "Done ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Choose.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(NewRent.this,"Machinery Already Rented",Toast.LENGTH_LONG).show();
        }}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected=parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }}
