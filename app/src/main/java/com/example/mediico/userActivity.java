package com.example.mediico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


public class userActivity extends Activity {

    EditText editTextUserName, editTextPhoneNumber;
    Button logIn;
    Button register;
    DatabaseReference databaseMedicine;
    DatabaseReference databaseUser;
    DatabaseReference dataOfUser;
    DatabaseReference dataVerify;
    String userName, phoneNumber, userId,logInUserName,logInPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FirebaseApp.initializeApp(this);
        databaseMedicine = FirebaseDatabase.getInstance().getReference("Medicine");
        //databasePharmacy=FirebaseDatabase.getInstance().getReference().child("Pharmacy");
        dataOfUser = FirebaseDatabase.getInstance().getReference().child("User");

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        logIn = (Button) findViewById(R.id.buttonLogIn);
        register = (Button) findViewById(R.id.buttonRegister);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logInUser();

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterUser();

            }
        });


    }

    private void RegisterUser() {
        userId = FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
        userName = editTextUserName.getText().toString();
        phoneNumber = editTextPhoneNumber.getText().toString();
        dataVerify = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("User Name").equalTo(userName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (!dataSnapshot.exists() && userName != null && phoneNumber != null) {
                    dataOfUser.child(userId).child("User Name").setValue(userName);
                    dataOfUser.child(userId).child("Phone Number").setValue(phoneNumber);
                    dataOfUser.child(userId).child("Purchase History").setValue(0);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();

                }
                //dataOfUser.child("User Id").setValue(userId);
                else {
                    Toast.makeText(getApplicationContext(), "You have registered before.Please Login Or Username already exists", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });


    }

    private void logInUser()
    {
         logInUserName = editTextUserName.getText().toString();
        logInPhoneNumber=editTextPhoneNumber.getText().toString();
        dataVerify=FirebaseDatabase.getInstance().getReference().child("User");
        Query query=FirebaseDatabase.getInstance().getReference().child("User").orderByChild("User Name").equalTo(userName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists() && logInUserName!=null && logInPhoneNumber!=null){
                    Toast.makeText(getApplicationContext() , "Please register or use valid username and phonenumber", Toast.LENGTH_LONG).show();

                }
                else
                {
                    //activity inserted hobe.
                    startActivity(new Intent(userActivity.this,medicine_show .class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });

    }

    /*    Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(userActivity.this,medicine_show .class));
            }
        });
    }
}*/
}
