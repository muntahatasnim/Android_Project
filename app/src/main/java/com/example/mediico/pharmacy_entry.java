package com.example.mediico;
import android.app.Activity;
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

import static android.content.ContentValues.TAG;


public class pharmacy_entry extends Activity {
    EditText editTextPName, editTextPhone, editTextAvailable;
    Button buttonPharma;
    //DatabaseReference mediicoRoot;
    DatabaseReference databasePharmacy;
    DatabaseReference databaseMedicine;
    DatabaseReference dataPharmacy;
    String pharmacyName, phoneNumber, address, pID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_entry);
        FirebaseApp.initializeApp(this);
        databaseMedicine = FirebaseDatabase.getInstance().getReference("Medicine");
        dataPharmacy = FirebaseDatabase.getInstance().getReference().child("Pharmacy");
        editTextPName = (EditText) findViewById(R.id.pharmacy_name);
        editTextPhone = (EditText) findViewById(R.id.phone_no);
        editTextAvailable = (EditText) findViewById(R.id.available);
        buttonPharma = (Button) findViewById(R.id.add_pharmacy);
        buttonPharma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AddMedicine();
                AddPharmacy();

            }
        });
    }


    public void AddPharmacy() {
        pID = FirebaseDatabase.getInstance().getReference().child("Pharmacy").push().getKey();
        pharmacyName = editTextPName.getText().toString();
        phoneNumber = editTextPhone.getText().toString();
        address = editTextAvailable.getText().toString();
        dataPharmacy = FirebaseDatabase.getInstance().getReference().child("Pharmacy");
        Query query = FirebaseDatabase.getInstance().getReference().child("Pharmacy").orderByChild("Pharmacy Name").equalTo(pharmacyName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (!dataSnapshot.exists()) {
                    dataPharmacy.child(pID).child("User Name").setValue(pharmacyName);
                    dataPharmacy.child(pID).child("Phone Number").setValue(phoneNumber);
                    dataPharmacy.child(pID).child("Address").setValue(0);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();

                }
                //dataOfUser.child("User Id").setValue(userId);
                else {
                    Toast.makeText(getApplicationContext(), "You have registered before", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });

    }
}