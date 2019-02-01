package com.example.mediico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class medicine_pharmacy_add extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_pharmacy_add);

        Button addMed = (Button) findViewById(R.id.InputMedicine);
        addMed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(medicine_pharmacy_add.this, admin_medicine_reg.class));
            }
        });

        Button addpharma = (Button) findViewById(R.id.InputPharmacy);
        addpharma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(medicine_pharmacy_add.this, pharmacy_entry.class));
            }
        });



    }
}
