package com.chaturvediji.dealer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
public class NewMachine extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText man,cond,model,dop,rpd;
    Spinner ty;
    Button btn;
    String date,type;
    FirebaseAuth mAuth;
    DatabaseReference databaseMac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_machine);
        mAuth=FirebaseAuth.getInstance();
        btn=(Button)findViewById(R.id.button6);
        ty=(Spinner) findViewById(R.id.editText5);
        man=(EditText) findViewById(R.id.editText6);
        model=(EditText) findViewById(R.id.editText13);
        dop=(EditText) findViewById(R.id.editText11);
        rpd=(EditText) findViewById(R.id.editText8);
        cond=(EditText) findViewById(R.id.editText9);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        dop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        NewMachine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        date=day+"/"+month+"/"+year;
                        dop.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }});
        databaseMac= FirebaseDatabase.getInstance().getReference("Machine");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMachine();
            }
        });
        ty.setOnItemSelectedListener(this);
    }
    private void AddMachine() {
        String manuf=man.getText().toString();
        String mod=model.getText().toString();
        String rent=rpd.getText().toString();
        String condi=cond.getText().toString();
        String id=databaseMac.push().getKey();
        String sid=mAuth.getUid();
        MachineAddition machineAddition=new MachineAddition(id,type,manuf,mod,date,rent,condi,"null",sid);
        databaseMac.child(id).setValue(machineAddition);
        Toast.makeText(this,"Machine added successfully",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(NewMachine.this,Choose.class);
        startActivity(intent);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type=parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}}
