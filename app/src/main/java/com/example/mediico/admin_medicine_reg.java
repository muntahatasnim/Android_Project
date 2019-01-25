package com.example.mediico;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;


public class admin_medicine_reg extends Activity {
    EditText editTextBName, editTextGName, editTextAvailIn, editTextStock;
    Button buttonMed;
    //DatabaseReference mediicoRoot;
    //DatabaseReference databasePharmacy;
    DatabaseReference databaseMedicine;
    //DatabaseReference dd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_medicine_reg);
        FirebaseApp.initializeApp(this);
        databaseMedicine= FirebaseDatabase.getInstance().getReference("Medicine");
        //databasePharmacy=FirebaseDatabase.getInstance().getReference().child("Pharmacy");


        //FirebaseDatabase.getInstance().getReference().child("Pharmacy");
        //databasePharmacy = FirebaseDatabase.getInstance().getReference("Pharmacy");
        editTextBName = (EditText) findViewById(R.id.bName);
        editTextGName = (EditText) findViewById(R.id.gName);
        editTextAvailIn = (EditText) findViewById(R.id.availIn);
        editTextStock = (EditText) findViewById(R.id.stock);
        buttonMed = (Button) findViewById(R.id.add_medicine);
        buttonMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddMedicine();
                //AddPharmacy();

            }
        });

    }

    public boolean isInteger( String input ) { //Pass in string
        try { //Try to make the input into an integer
            Integer.parseInt( input );
            return true; //Return true if it works
        }
        catch( Exception e ) {
            return false; //If it doesn't work return false
        }
    }

    private void AddMedicine() {
        int stock;
        String brandName = editTextBName.getText().toString();

        String genericName = editTextGName.getText().toString();
        //System.out.println(genericName);
        String pharmacyName = editTextAvailIn.getText().toString();
        //System.out.println(pharmacyName);
        if (isInteger(editTextStock.getText().toString())) {
            stock = Integer.parseInt(editTextStock.getText().toString());
        }
        else{
            stock = 0;
        }
        System.out.println(stock);
        if (!TextUtils.isEmpty(brandName) &&!TextUtils.isEmpty(genericName) &&!TextUtils.isEmpty(pharmacyName) && stock!=0) {
            String id = databaseMedicine.push().getKey();
            com.example.mediico.Medicine medicine = new com.example.mediico.Medicine(id, brandName, genericName, pharmacyName, stock);
            assert id != null;
            databaseMedicine.child(id).setValue(medicine,new
                    DatabaseReference.CompletionListener() {

                        @Override
                        public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference
                                databaseReference) {

                            //Problem with saving the data
                            //System.out.println("Value was set ");

                        }
                    });
            Toast. makeText(this,"Added Medicine", LENGTH_SHORT).show();
        }

        else {
            Toast. makeText(this, "You Should Enter All Your Informations", LENGTH_SHORT).show();
        }

    }
}
