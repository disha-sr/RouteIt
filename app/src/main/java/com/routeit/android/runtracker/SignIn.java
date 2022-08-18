package com.raywenderlich.android.runtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignIn extends AppCompatActivity {
    TextInputLayout urname,password;
    Button btnSignIn;
DatabaseHelper2 myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        urname=findViewById(R.id.username);
        password=findViewById(R.id.password);
        btnSignIn=findViewById(R.id.btnSignIn);
        myDB=new DatabaseHelper2(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=urname.getEditText().getText().toString();
                String pass=password.getEditText().getText().toString();
                if(user.equals("") || pass.equals("") ){
                    Toast.makeText(SignIn.this, "Fill all the fields", Toast.LENGTH_SHORT).show();

                }else {
                 Boolean result=   myDB.checkusernamePassword(user,pass);
                 if(result==true){
                       Intent intent=new Intent(getApplicationContext(),PathCredentials.class);
                       startActivity(intent);
                 }else{
                     Toast.makeText(SignIn.this,"Invalid Credentials!",Toast.LENGTH_SHORT).show();

                 }
                }



                }
        });
    }
}