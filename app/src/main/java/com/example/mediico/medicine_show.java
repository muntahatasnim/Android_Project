package com.example.mediico;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class medicine_show extends Activity {
    private DatabaseReference medicinePageDataBaseRef;
    private ArrayList<String> medicineName = new ArrayList<>();
    private ArrayList<String>medicineKey = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_show);
        medicinePageDataBaseRef = FirebaseDatabase.getInstance().getReference("Medicine");

        medicinePageDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medicineName.clear();
                medicineKey.clear();

                int i=0;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Medicine medicine = ds.getValue(Medicine.class);
                    medicineName.add(medicine.getBrandName());
                    medicineKey.add(ds.getKey());
                }
                Collections.reverse(medicineKey);
                Collections.reverse(medicineName);
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view5);
        medicineListRecyclerViewAdapter adapter = new medicineListRecyclerViewAdapter(this,medicineName,medicineKey);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
