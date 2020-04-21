package com.chaturvediji.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {
    EditText et;
    EditText pt;
    FirebaseAuth mAuth;
    public void signup(View view){
        String email=et.getText().toString();
        String password=pt.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(MainActivity.this,NewCustomer.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }}});}
                        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        et=(EditText)findViewById(R.id.editText);
        pt=(EditText)findViewById(R.id.editText2);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(MainActivity.this,currentUser.getEmail(),Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,Choose.class);
            startActivity(intent);
        }}

    public void login(View view){
        Intent intent= new Intent(this,Main2Activity.class);
        startActivity(intent);
    }}
