package com.chaturvediji.dealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class MachineView extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseMac,databaseup;
    List<MachineAddition> machineAdditionList;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_view);
        listView=(ListView)findViewById(R.id.listviewmach);
        mAuth=FirebaseAuth.getInstance();
        databaseMac= FirebaseDatabase.getInstance().getReference("Machine");
        machineAdditionList=new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MachineAddition machineAddition=machineAdditionList.get(position);
                showdialog(machineAddition.getId());
            }});}
    @Override
    protected void onStart() {
        super.onStart();
        String id=mAuth.getUid();
        Query query=FirebaseDatabase.getInstance().getReference("Machine").orderByChild("sid").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                machineAdditionList.clear();
             for(DataSnapshot macsnapshot : dataSnapshot.getChildren()){
                 MachineAddition machineAddition=macsnapshot.getValue(MachineAddition.class);
                 machineAdditionList.add(machineAddition);
                 }
                MachList adapter=new MachList(MachineView.this,machineAdditionList);
             listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});}
    private void showdialog(final String id){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        final View dialogview=inflater.inflate(R.layout.dialog_layout,null);
        builder.setView(dialogview);
        final EditText editTextrent=(EditText)dialogview.findViewById(R.id.editText7);
        final Button btnup=(Button)dialogview.findViewById(R.id.button10);
        final Button btndel=(Button)dialogview.findViewById(R.id.button11);
        builder.setTitle("Update "+id);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseup=FirebaseDatabase.getInstance().getReference("Machine").child(id).child("rent");
                databaseup.setValue(editTextrent.getText().toString());
                alertDialog.dismiss();
            }});
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseup=FirebaseDatabase.getInstance().getReference("Machine").child(id);
                databaseup.removeValue();
                alertDialog.dismiss();
            }
        });}}
