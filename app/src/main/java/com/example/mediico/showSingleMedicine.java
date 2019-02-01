package com.example.mediico;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showSingleMedicine extends Activity {

    private DatabaseReference showSingleMedicineDatabaseRef;

    TextView medName;
    TextView medStock;
    private DatabaseReference userDatabase;
    String key;
    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_medicine);
        medName = findViewById(R.id.medicine_value);
        medStock = findViewById(R.id.quantity_value);
        key = getIntent().getStringExtra("showKey");
        //batch =getIntent().getStringExtra("batch");
        showSingleMedicineDatabaseRef = FirebaseDatabase.getInstance().getReference("Medicine/" + key);


        showSingleMedicineDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medicine = dataSnapshot.getValue(Medicine.class);
                medName.setText(medicine.getBrandName());
                medStock.setText(String.valueOf(medicine.getStock()));
                showSingleMedicineDatabaseRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

     final Button cartButton = (Button) findViewById(R.id.cart);
    final Button plusButton=findViewById(R.id.plus);
     plusButton.setVisibility(View.GONE);
        final Button minusButton=findViewById(R.id.minus);
        minusButton.setVisibility(View.GONE);
        cartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cartButton.setVisibility(View.GONE);
                minusButton.setVisibility(View.VISIBLE);
                plusButton.setVisibility(View.VISIBLE);


            }
        });



    }
}

