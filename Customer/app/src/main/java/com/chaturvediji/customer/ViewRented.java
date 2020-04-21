package com.chaturvediji.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
public class ViewRented extends AppCompatActivity {
    ListView vrlv;
    FirebaseAuth mAuth;
    List<Machine> machineAdditionList;
    DatabaseReference databaseMac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rented);
        vrlv=(ListView)findViewById(R.id.viewretedlv);
        mAuth=FirebaseAuth.getInstance();
        machineAdditionList=new ArrayList<>();
        vrlv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Machine machine=machineAdditionList.get(position);
                databaseMac= FirebaseDatabase.getInstance().getReference("Machine").child(machine.getId()).child("cid");
                databaseMac.setValue("null");
                Toast.makeText(ViewRented.this,"Machine Returned",Toast.LENGTH_LONG).show();
                return true;
            }
        } );}
    @Override
    protected void onStart() {
        super.onStart();
        String id=mAuth.getUid();
        Query query= FirebaseDatabase.getInstance().getReference("Machine")
                .orderByChild("cid").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                machineAdditionList.clear();
                for(DataSnapshot macsnapshot : dataSnapshot.getChildren()){
                    Machine machineAddition=macsnapshot.getValue(Machine.class);
                    machineAdditionList.add(machineAddition);
                }
                MachList adapter=new MachList(ViewRented.this,machineAdditionList);
                vrlv.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});}}
