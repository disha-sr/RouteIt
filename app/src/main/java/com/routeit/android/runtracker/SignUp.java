package com.raywenderlich.android.runtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
TextInputLayout urname,pasword,repassword;
Button btnSignUp,btnSignIn;
DatabaseHelper2 myDB2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        urname= findViewById(R.id.username);
        pasword= findViewById(R.id.password);
        repassword= findViewById(R.id.repassword);

        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        myDB2=new DatabaseHelper2(this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=urname.getEditText().getText().toString();
                String pass=pasword.getEditText().getText().toString();
                String repass=repassword.getEditText().getText().toString();
                
                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(SignUp.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass))
                    {
                      Boolean usercheckresult = myDB2.checkusername(user);
                      if(usercheckresult==false)
                      {
                         Boolean regResult=myDB2.insertData(user,pass);
                         if(regResult==true)
                         {
                             Toast.makeText(SignUp.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                             Intent intent=new Intent(getApplicationContext(),SignIn.class);
                             startActivity(intent);
                         }else{
                             Toast.makeText(SignUp.this,"Registration Failed!",Toast.LENGTH_SHORT).show();

                         }
                      }
                      else
                      {
                          Toast.makeText(SignUp.this,"User already exists.\n Please Sign in",Toast.LENGTH_SHORT).show();
                      }
                    }
                    else
                    {
                        Toast.makeText(SignUp.this,"Password not Matching",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
            }
        });

    }
}