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


public class DeliverymanActivity extends Activity {
    EditText editTextDMName, editTextDMNumber, editTextDMHome, editTextDMWork;
    Button register;
    DatabaseReference databaseMedicine;
    DatabaseReference databaseDM;
    DatabaseReference dataOfDM;
    DatabaseReference dataVerifyDM;
    String dMName, dMPhone, dMWork, dMHome, dMId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryman);
        FirebaseApp.initializeApp(this);
        databaseMedicine = FirebaseDatabase.getInstance().getReference("Medicine");
        //databasePharmacy=FirebaseDatabase.getInstance().getReference().child("Pharmacy");
        dataOfDM = FirebaseDatabase.getInstance().getReference().child("Delivery Man");
        editTextDMName = (EditText) findViewById(R.id.name);
        editTextDMNumber = (EditText) findViewById(R.id.phone);
        editTextDMHome = (EditText) findViewById(R.id.hometown);
        editTextDMWork = (EditText) findViewById(R.id.workarea);
        register = (Button) findViewById(R.id.buttonRegisterDM);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterDeliveryMan();

            }
        });

    }


    protected void RegisterDeliveryMan() {
        dMId = FirebaseDatabase.getInstance().getReference().child("User").push().getKey();
        dMName = editTextDMName.getText().toString();
        dMPhone = editTextDMNumber.getText().toString();
        dMHome = editTextDMHome.getText().toString();
        dMWork = editTextDMWork.getText().toString();
        dataVerifyDM = FirebaseDatabase.getInstance().getReference().child("Delivery Man");
        Query query = FirebaseDatabase.getInstance().getReference().child("Delivery Man").orderByChild("User Name").equalTo(dMName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (!dataSnapshot.exists()) {
                    dataOfDM.child(dMId).child("User Name").setValue(dMName);
                    dataOfDM.child(dMId).child("Phone Number").setValue(dMPhone);
                    dataOfDM.child(dMId).child("Address").setValue(dMHome);
                    dataOfDM.child(dMId).child("Area of Operation").setValue(dMWork);
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
