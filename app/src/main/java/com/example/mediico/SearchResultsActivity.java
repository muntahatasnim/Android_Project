package com.example.mediico;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class SearchResultsActivity extends Activity {


    TextView medName, pharmName, quantity, phoneNo, Cost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        phoneNo = findViewById(R.id.phoneValue);
        medName = findViewById(R.id.brandNameValue);
        pharmName = findViewById(R.id.pharmacyNameValue);
        quantity = findViewById(R.id.quantityValue);
        Cost = findViewById(R.id.priceValue);
        String queryName = getIntent().getStringExtra("showKey");
        System.out.println(queryName);
        Query query = FirebaseDatabase.getInstance().getReference().child("Medicine").orderByChild("brandName").equalTo(queryName);
        DatabaseReference dataOfMed = FirebaseDatabase.getInstance().getReference().child("Medicine");
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Medicine medicine = childSnapshot.getValue(Medicine.class);

                    medName.setText(medicine.getBrandName());
                    pharmName.setText(medicine.getAvailableIn());
                    quantity.setText(String.valueOf(medicine.getStock()));
                    Cost.setText(String.valueOf(medicine.getPrice()));
                    Query queryPharma = FirebaseDatabase.getInstance().getReference().child("Pharmacy").orderByChild("User Name").equalTo(medicine.getAvailableIn());
                    queryPharma.addListenerForSingleValueEvent(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                         if(dataSnapshot.exists())
                         {
                             Pharmacy pharmacy = dataSnapshot.getValue(Pharmacy.class);
                             phoneNo.setText(pharmacy.getPhoneNo());
                         }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.d(TAG, databaseError.getMessage());
                        }
                    });


                    //dataOfUser.child("User Id").setValue(userId);


                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });


    }


    }
