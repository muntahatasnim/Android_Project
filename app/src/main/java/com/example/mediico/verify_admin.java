package com.example.mediico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class verify_admin extends Activity {
    EditText editTextAdmin, editTextPassword;
    Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_admin);
        editTextAdmin = (EditText) findViewById(R.id.editTextAdmin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        logIn = (Button) findViewById(R.id.buttonLogIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logInAdmin();

            }
        });

    }
    private void logInAdmin()
    {
        String userName=editTextAdmin.getText().toString();
        String password=editTextPassword.getText().toString();
        if(userName.equals("MediicoAdmin") && password.equals("hehe1234"))
        {
            startActivity(new Intent(verify_admin.this, medicine_pharmacy_add.class));
        }
        else
        {
            Toast.makeText(getApplicationContext() , "Please Register through regular account", Toast.LENGTH_SHORT).show();
        }


    }
}
